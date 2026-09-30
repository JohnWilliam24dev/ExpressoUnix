package com.johnwilliam.ExpressoUnix.Applications;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.johnwilliam.ExpressoUnix.DTO.PagamentoRequestDTO;
import com.johnwilliam.ExpressoUnix.Enums.FormaPagamento;
import com.johnwilliam.ExpressoUnix.Enums.StatusPagamento;
import com.johnwilliam.ExpressoUnix.Exceptions.BusinessException;
import com.johnwilliam.ExpressoUnix.Models.PagamentoModels;
import com.johnwilliam.ExpressoUnix.Repositories.PagamentoRepository;

/**
 * Pagamentos da venda (PAG-01, PAG-02, PAG-04). Nao ha gateway: todo pagamento valido e registrado como Aprovado.
 * As regras sao metodos estaticos puros para poderem ser testados sem Spring.
 */
@Service
public class PagamentoApplication {
    private final PagamentoRepository pagamentoRepository;

    public PagamentoApplication(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    /** Registra os pagamentos ja validados de uma venda, calculando o troco dos pagamentos em dinheiro. */
    public List<PagamentoModels> registrar(long idVenda, List<PagamentoRequestDTO> pagamentos) {
        List<PagamentoModels> registrados = new ArrayList<>();
        for (PagamentoRequestDTO dto : pagamentos) {
            PagamentoModels pagamento = new PagamentoModels();
            pagamento.setIdVenda(idVenda);
            pagamento.setForma(dto.getForma());
            pagamento.setValor(dto.getValor());
            pagamento.setValorRecebido(dto.getValorRecebido());
            pagamento.setTroco(calcularTroco(dto));
            pagamento.setStatus(StatusPagamento.Aprovado);
            registrados.add(pagamentoRepository.createPagamento(pagamento));
        }
        return registrados;
    }

    /** Regras de cada pagamento isolado: valorRecebido so existe (e e obrigatorio) em DINHEIRO e cobre o valor. */
    public static void validarPagamento(PagamentoRequestDTO pagamento, int indice) {
        if (pagamento.getForma() == FormaPagamento.DINHEIRO) {
            if (pagamento.getValorRecebido() == null) {
                throw new BusinessException("pagamentos[" + indice + "]: pagamento em DINHEIRO exige valorRecebido");
            }
            if (pagamento.getValorRecebido().compareTo(pagamento.getValor()) < 0) {
                throw new BusinessException("pagamentos[" + indice + "]: valorRecebido nao pode ser menor que o valor");
            }
        } else if (pagamento.getValorRecebido() != null) {
            throw new BusinessException("pagamentos[" + indice + "]: valorRecebido so se aplica a pagamento em DINHEIRO");
        }
    }

    /** A soma dos valores deve ser exatamente o total da venda; sobra so existe como troco de dinheiro. */
    public static void validarCobertura(BigDecimal valorTotal, List<PagamentoRequestDTO> pagamentos) {
        BigDecimal soma = BigDecimal.ZERO;
        for (PagamentoRequestDTO pagamento : pagamentos) {
            soma = soma.add(pagamento.getValor());
        }
        int comparacao = soma.compareTo(valorTotal);
        if (comparacao < 0) {
            throw new BusinessException("Os pagamentos (" + soma + ") nao cobrem o valor total da venda (" + valorTotal + ")");
        }
        if (comparacao > 0) {
            throw new BusinessException("Os pagamentos (" + soma + ") excedem o valor total da venda (" + valorTotal
                    + "); a diferenca deve ser informada em valorRecebido (dinheiro), nunca em valor");
        }
    }

    /** troco = valorRecebido - valor (so DINHEIRO); nunca negativo. */
    public static BigDecimal calcularTroco(PagamentoRequestDTO pagamento) {
        if (pagamento.getForma() != FormaPagamento.DINHEIRO || pagamento.getValorRecebido() == null) {
            return BigDecimal.ZERO.setScale(2);
        }
        return pagamento.getValorRecebido().subtract(pagamento.getValor()).max(BigDecimal.ZERO).setScale(2);
    }
}
