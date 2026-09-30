package com.johnwilliam.ExpressoUnix.Applications;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.johnwilliam.ExpressoUnix.DTO.CotacaoDTO;
import com.johnwilliam.ExpressoUnix.DTO.ItemVendaDTO;
import com.johnwilliam.ExpressoUnix.DTO.PagamentoRequestDTO;
import com.johnwilliam.ExpressoUnix.DTO.VendaDTO;
import com.johnwilliam.ExpressoUnix.DTO.VendaRequestDTO;
import com.johnwilliam.ExpressoUnix.Enums.StatusAssento;
import com.johnwilliam.ExpressoUnix.Enums.StatusPassagem;
import com.johnwilliam.ExpressoUnix.Enums.StatusVenda;
import com.johnwilliam.ExpressoUnix.Enums.TipoTarifa;
import com.johnwilliam.ExpressoUnix.Enums.TipoTrecho;
import com.johnwilliam.ExpressoUnix.Exceptions.BusinessException;
import com.johnwilliam.ExpressoUnix.Exceptions.ConflictException;
import com.johnwilliam.ExpressoUnix.Mappers.VendaMapper;
import com.johnwilliam.ExpressoUnix.Models.AssentoModels;
import com.johnwilliam.ExpressoUnix.Models.PagamentoModels;
import com.johnwilliam.ExpressoUnix.Models.PassagemModels;
import com.johnwilliam.ExpressoUnix.Models.VendaModels;
import com.johnwilliam.ExpressoUnix.Models.ViagemModels;
import com.johnwilliam.ExpressoUnix.Repositories.AssentoRepository;
import com.johnwilliam.ExpressoUnix.Repositories.FuncionarioRepository;
import com.johnwilliam.ExpressoUnix.Repositories.PagamentoRepository;
import com.johnwilliam.ExpressoUnix.Repositories.PassageiroRepository;
import com.johnwilliam.ExpressoUnix.Repositories.PassagemRepository;
import com.johnwilliam.ExpressoUnix.Repositories.VendaRepository;
import com.johnwilliam.ExpressoUnix.Repositories.ViagemRepository;

@Service
public class VendaApplication {
    private final VendaRepository vendaRepository;
    private final VendaMapper vendaMapper;
    private final FuncionarioRepository funcionarioRepository;
    private final PassagemRepository passagemRepository;
    private final PassageiroRepository passageiroRepository;
    private final ViagemRepository viagemRepository;
    private final AssentoRepository assentoRepository;
    private final AssentoApplication assentoApplication;
    private final PrecoApplication precoApplication;
    private final PagamentoApplication pagamentoApplication;
    private final PagamentoRepository pagamentoRepository;
    private final ValidadorTrechos validadorTrechos;

    public VendaApplication(VendaRepository vendaRepository, VendaMapper vendaMapper,
                            FuncionarioRepository funcionarioRepository, PassagemRepository passagemRepository,
                            PassageiroRepository passageiroRepository, ViagemRepository viagemRepository,
                            AssentoRepository assentoRepository, AssentoApplication assentoApplication,
                            PrecoApplication precoApplication, PagamentoApplication pagamentoApplication,
                            PagamentoRepository pagamentoRepository, ValidadorTrechos validadorTrechos) {
        this.vendaRepository = vendaRepository;
        this.vendaMapper = vendaMapper;
        this.funcionarioRepository = funcionarioRepository;
        this.passagemRepository = passagemRepository;
        this.passageiroRepository = passageiroRepository;
        this.viagemRepository = viagemRepository;
        this.assentoRepository = assentoRepository;
        this.assentoApplication = assentoApplication;
        this.precoApplication = precoApplication;
        this.pagamentoApplication = pagamentoApplication;
        this.pagamentoRepository = pagamentoRepository;
        this.validadorTrechos = validadorTrechos;
    }

    /**
     * Cria a venda com N passagens em uma unica transacao (tudo ou nada, VEN-01):
     * qualquer falha desfaz a venda, as passagens e a ocupacao dos assentos.
     *
     * Os pagamentos vao no mesmo request e precisam cobrir exatamente o total calculado pelo servidor;
     * como tudo e validado antes de gravar, a venda so existe ja paga: nasce Finalizada e as passagens Emitidas
     * (o estado Aberta/Reservada so faz sentido com a reserva temporaria, fase 2).
     */
    @Transactional
    public VendaDTO createVenda(VendaRequestDTO request) {
        List<ItemVendaDTO> itens = request.getItens();

        funcionarioRepository.getFuncionarioById(request.getIdFuncionario()); // 404
        validadorTrechos.validarEstrutura(itens); // 400
        List<PagamentoRequestDTO> pagamentos = request.getPagamentos();
        for (int i = 0; i < pagamentos.size(); i++) {
            PagamentoApplication.validarPagamento(pagamentos.get(i), i); // 400
        }

        List<ViagemModels> viagens = new ArrayList<>();
        List<CotacaoDTO> cotacoes = new ArrayList<>();
        BigDecimal totalCotado = BigDecimal.ZERO;
        for (int i = 0; i < itens.size(); i++) {
            viagens.add(validarItem(itens.get(i), i));
            CotacaoDTO cotacao = precoApplication.cotar(itens.get(i).getIdViagem(), tarifaDe(itens.get(i))); // 400 sem rota
            cotacoes.add(cotacao);
            totalCotado = totalCotado.add(cotacao.getPreco());
        }
        validadorTrechos.validarCoerencia(itens, viagens);
        PagamentoApplication.validarCobertura(totalCotado, pagamentos); // 400

        VendaModels venda = new VendaModels();
        venda.setIdFuncionario(request.getIdFuncionario());
        venda.setStatus(StatusVenda.Finalizada);
        venda = vendaRepository.createVenda(venda);

        // IDA/AVULSA primeiro: a VOLTA precisa do ID da IDA ja salva para o vinculo informativo
        PassagemModels[] emitidas = new PassagemModels[itens.size()];
        for (int fase = 0; fase < 2; fase++) {
            for (int i = 0; i < itens.size(); i++) {
                ItemVendaDTO item = itens.get(i);
                boolean volta = item.getTipoTrecho() == TipoTrecho.VOLTA;
                if (volta != (fase == 1)) {
                    continue;
                }
                Long idIda = volta ? emitidas[item.getVinculadaAoItem()].getId() : null;
                emitidas[i] = emitirPassagem(venda.getId(), item, viagens.get(i), cotacoes.get(i), idIda);
            }
        }

        BigDecimal total = BigDecimal.ZERO;
        BigDecimal descontoTotal = BigDecimal.ZERO;
        for (PassagemModels passagem : emitidas) {
            total = total.add(passagem.getValorPago());
            descontoTotal = descontoTotal.add(passagem.getDesconto());
        }
        venda.setValorTotal(total);
        venda.setDescontoTotal(descontoTotal);
        vendaRepository.updateVenda(venda);

        List<PagamentoModels> registrados = pagamentoApplication.registrar(venda.getId(), pagamentos);

        return vendaMapper.modelToDTO(venda, List.of(emitidas), registrados);
    }

    public VendaDTO getVendaById(long id) {
        VendaModels venda = vendaRepository.getVendaById(id);
        return vendaMapper.modelToDTO(venda, passagemRepository.getByVenda(id), pagamentoRepository.getByVenda(id));
    }

    public List<VendaDTO> getAllVenda() {
        List<VendaDTO> vendas = new ArrayList<>();
        for (VendaModels venda : vendaRepository.getAllVenda()) {
            vendas.add(vendaMapper.modelToDTO(venda, passagemRepository.getByVenda(venda.getId()),
                    pagamentoRepository.getByVenda(venda.getId())));
        }
        return vendas;
    }

    /** VEN-04: viagem, passageiro e assento existem (404); assento pertence a viagem (400) e esta livre (409). */
    private ViagemModels validarItem(ItemVendaDTO item, int indice) {
        ViagemModels viagem = viagemRepository.getViagemById(item.getIdViagem());
        passageiroRepository.getPassageiroById(item.getIdPassageiro());
        AssentoModels assento = assentoRepository.getAssentoById(item.getIdAssento());

        if (assento.getIdViagem() != item.getIdViagem()) {
            throw new BusinessException("itens[" + indice + "]: o assento informado nao pertence a viagem informada");
        }
        if (assento.getStatusAssento() != StatusAssento.Livre) {
            throw new ConflictException("O assento " + assento.getNumeroAssento() + " da viagem "
                    + item.getIdViagem() + " ja esta ocupado");
        }
        return viagem;
    }

    private TipoTarifa tarifaDe(ItemVendaDTO item) {
        return item.getTipoTarifa() == null ? TipoTarifa.INTEIRA : item.getTipoTarifa();
    }

    private PassagemModels emitirPassagem(long idVenda, ItemVendaDTO item, ViagemModels viagem,
                                          CotacaoDTO cotacao, Long idPassagemIda) {
        TipoTarifa tipoTarifa = tarifaDe(item);

        PassagemModels passagem = new PassagemModels();
        passagem.setStatus(StatusPassagem.Emitida);
        passagem.setIdVenda(idVenda);
        passagem.setTipoTrecho(item.getTipoTrecho());
        passagem.setIdPassagemVinculada(idPassagemIda);
        passagem.setIdViagem(item.getIdViagem());
        passagem.setIdAssento(item.getIdAssento());
        passagem.setIdPassageiro(item.getIdPassageiro());
        passagem.setDataPassagem(viagem.getDataViagem());
        passagem.setHoraPassagem(viagem.getHoraViagem());
        passagem.setOrigem(viagem.getOrigem());
        passagem.setDestino(viagem.getDestino());
        passagem.setDistancia(cotacao.getDistanciaKm());
        passagem.setTipoTarifa(tipoTarifa);
        passagem.setTarifaBase(cotacao.getTarifaBase());
        passagem.setDesconto(cotacao.getDesconto());
        passagem.setValorPago(cotacao.getPreco());

        PassagemModels salva = passagemRepository.createPassagem(passagem);
        assentoApplication.alterarStatus(item.getIdAssento(), StatusAssento.Ocupado);
        return salva;
    }
}
