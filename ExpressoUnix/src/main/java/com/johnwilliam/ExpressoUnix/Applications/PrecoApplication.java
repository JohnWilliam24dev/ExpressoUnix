package com.johnwilliam.ExpressoUnix.Applications;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.johnwilliam.ExpressoUnix.DTO.CotacaoDTO;
import com.johnwilliam.ExpressoUnix.Enums.TipoTarifa;
import com.johnwilliam.ExpressoUnix.Exceptions.BusinessException;
import com.johnwilliam.ExpressoUnix.Models.RotaModels;
import com.johnwilliam.ExpressoUnix.Models.TabelaPrecoModels;
import com.johnwilliam.ExpressoUnix.Models.VeiculoModels;
import com.johnwilliam.ExpressoUnix.Models.ViagemModels;
import com.johnwilliam.ExpressoUnix.Repositories.RotaRepository;
import com.johnwilliam.ExpressoUnix.Repositories.TabelaPrecoRepository;
import com.johnwilliam.ExpressoUnix.Repositories.VeiculoRepository;
import com.johnwilliam.ExpressoUnix.Repositories.ViagemRepository;

/**
 * Calculo de preco no servidor (CAT-04 / VEN-05).
 * preco = precoBase da rota x multiplicador da classe x percentual do tipo de tarifa.
 */
@Service
public class PrecoApplication {
    private final ViagemRepository viagemRepository;
    private final VeiculoRepository veiculoRepository;
    private final RotaRepository rotaRepository;
    private final TabelaPrecoRepository tabelaPrecoRepository;

    public PrecoApplication(ViagemRepository viagemRepository, VeiculoRepository veiculoRepository,
                            RotaRepository rotaRepository, TabelaPrecoRepository tabelaPrecoRepository) {
        this.viagemRepository = viagemRepository;
        this.veiculoRepository = veiculoRepository;
        this.rotaRepository = rotaRepository;
        this.tabelaPrecoRepository = tabelaPrecoRepository;
    }

    /** Cota o preco de um assento na viagem. 404 se a viagem nao existe; 400 se faltar rota ou tabela de preco. */
    public CotacaoDTO cotar(long idViagem, TipoTarifa tipoTarifa) {
        ViagemModels viagem = viagemRepository.getViagemById(idViagem);
        VeiculoModels veiculo = veiculoRepository.getVeiculoById(viagem.getIdVeiculo());

        RotaModels rota = rotaRepository.findByOrigemDestino(viagem.getOrigem(), viagem.getDestino())
                .orElseThrow(() -> new BusinessException("Nao existe rota cadastrada de "
                        + viagem.getOrigem() + " para " + viagem.getDestino()));

        TabelaPrecoModels tabela = tabelaPrecoRepository.findByClasse(veiculo.getClasse())
                .orElseThrow(() -> new BusinessException(
                        "Nao existe multiplicador de preco cadastrado para a classe " + veiculo.getClasse()));

        CotacaoDTO cotacao = new CotacaoDTO();
        cotacao.setIdViagem(idViagem);
        cotacao.setOrigem(viagem.getOrigem());
        cotacao.setDestino(viagem.getDestino());
        cotacao.setClasse(veiculo.getClasse());
        cotacao.setTipoTarifa(tipoTarifa);
        cotacao.setDistanciaKm(rota.getDistanciaKm());
        cotacao.setPrecoBase(rota.getPrecoBase());
        cotacao.setMultiplicadorClasse(tabela.getMultiplicador());
        cotacao.setPercentualTarifa(tipoTarifa.getPercentual());
        cotacao.setPreco(calcularPreco(rota.getPrecoBase(), tabela.getMultiplicador(), tipoTarifa));
        return cotacao;
    }

    /** Formula pura do preco, arredondada para 2 casas (HALF_UP). */
    public static BigDecimal calcularPreco(BigDecimal precoBase, BigDecimal multiplicadorClasse, TipoTarifa tipoTarifa) {
        return precoBase
                .multiply(multiplicadorClasse)
                .multiply(tipoTarifa.getPercentual())
                .setScale(2, RoundingMode.HALF_UP);
    }
}
