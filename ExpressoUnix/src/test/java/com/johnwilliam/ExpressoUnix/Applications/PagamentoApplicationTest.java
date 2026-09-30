package com.johnwilliam.ExpressoUnix.Applications;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.johnwilliam.ExpressoUnix.DTO.PagamentoRequestDTO;
import com.johnwilliam.ExpressoUnix.Enums.FormaPagamento;
import com.johnwilliam.ExpressoUnix.Exceptions.BusinessException;

class PagamentoApplicationTest {

    private PagamentoRequestDTO pagamento(FormaPagamento forma, String valor, String recebido) {
        PagamentoRequestDTO dto = new PagamentoRequestDTO();
        dto.setForma(forma);
        dto.setValor(new BigDecimal(valor));
        dto.setValorRecebido(recebido == null ? null : new BigDecimal(recebido));
        return dto;
    }

    @Test
    void trocoEmDinheiroEDiferencaEntreRecebidoEValor() {
        assertEquals(new BigDecimal("10.00"),
                PagamentoApplication.calcularTroco(pagamento(FormaPagamento.DINHEIRO, "90.00", "100.00")));
    }

    @Test
    void trocoEZeroQuandoValorRecebidoEExato() {
        assertEquals(new BigDecimal("0.00"),
                PagamentoApplication.calcularTroco(pagamento(FormaPagamento.DINHEIRO, "90.00", "90.00")));
    }

    @Test
    void trocoEZeroParaPixECartao() {
        assertEquals(new BigDecimal("0.00"),
                PagamentoApplication.calcularTroco(pagamento(FormaPagamento.PIX, "90.00", null)));
    }

    @Test
    void dinheiroSemValorRecebidoEhRejeitado() {
        assertThrows(BusinessException.class,
                () -> PagamentoApplication.validarPagamento(pagamento(FormaPagamento.DINHEIRO, "90.00", null), 0));
    }

    @Test
    void valorRecebidoMenorQueValorEhRejeitado() {
        assertThrows(BusinessException.class,
                () -> PagamentoApplication.validarPagamento(pagamento(FormaPagamento.DINHEIRO, "90.00", "80.00"), 0));
    }

    @Test
    void valorRecebidoEmPixEhRejeitado() {
        assertThrows(BusinessException.class,
                () -> PagamentoApplication.validarPagamento(pagamento(FormaPagamento.PIX, "90.00", "100.00"), 0));
    }

    @Test
    void pixECartaoSemValorRecebidoSaoValidos() {
        assertDoesNotThrow(() -> PagamentoApplication.validarPagamento(pagamento(FormaPagamento.PIX, "90.00", null), 0));
        assertDoesNotThrow(() -> PagamentoApplication.validarPagamento(pagamento(FormaPagamento.CREDITO, "90.00", null), 1));
    }

    @Test
    void somaIgualAoTotalCobreAVenda() {
        List<PagamentoRequestDTO> pagamentos = List.of(
                pagamento(FormaPagamento.DINHEIRO, "50.00", "50.00"),
                pagamento(FormaPagamento.PIX, "40.00", null));
        assertDoesNotThrow(() -> PagamentoApplication.validarCobertura(new BigDecimal("90.0"), pagamentos));
    }

    @Test
    void somaMenorQueOTotalEhRejeitada() {
        List<PagamentoRequestDTO> pagamentos = List.of(pagamento(FormaPagamento.PIX, "80.00", null));
        assertThrows(BusinessException.class,
                () -> PagamentoApplication.validarCobertura(new BigDecimal("90.00"), pagamentos));
    }

    @Test
    void somaMaiorQueOTotalEhRejeitada() {
        List<PagamentoRequestDTO> pagamentos = List.of(pagamento(FormaPagamento.PIX, "100.00", null));
        assertThrows(BusinessException.class,
                () -> PagamentoApplication.validarCobertura(new BigDecimal("90.00"), pagamentos));
    }
}
