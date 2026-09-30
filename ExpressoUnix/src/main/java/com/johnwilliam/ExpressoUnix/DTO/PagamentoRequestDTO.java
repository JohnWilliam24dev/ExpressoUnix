package com.johnwilliam.ExpressoUnix.DTO;

import java.math.BigDecimal;

import com.johnwilliam.ExpressoUnix.Enums.FormaPagamento;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PagamentoRequestDTO {

    @NotNull(message = "forma e obrigatoria (DINHEIRO, DEBITO, CREDITO ou PIX)")
    private FormaPagamento forma;

    @NotNull(message = "valor e obrigatorio")
    @Positive(message = "valor deve ser maior que zero")
    @Digits(integer = 8, fraction = 2, message = "valor aceita ate 8 inteiros e 2 decimais")
    private BigDecimal valor;

    /** Obrigatorio e somente valido para DINHEIRO. */
    @Positive(message = "valorRecebido deve ser maior que zero")
    @Digits(integer = 8, fraction = 2, message = "valorRecebido aceita ate 8 inteiros e 2 decimais")
    private BigDecimal valorRecebido;

    public PagamentoRequestDTO() {}

    public FormaPagamento getForma() { return forma; }
    public void setForma(FormaPagamento forma) { this.forma = forma; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public BigDecimal getValorRecebido() { return valorRecebido; }
    public void setValorRecebido(BigDecimal valorRecebido) { this.valorRecebido = valorRecebido; }
}
