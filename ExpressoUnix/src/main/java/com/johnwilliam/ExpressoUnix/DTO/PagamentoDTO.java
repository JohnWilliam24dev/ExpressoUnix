package com.johnwilliam.ExpressoUnix.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.johnwilliam.ExpressoUnix.Enums.FormaPagamento;
import com.johnwilliam.ExpressoUnix.Enums.StatusPagamento;

/** Somente saida. */
public class PagamentoDTO {
    private Long id;
    private long idVenda;
    private FormaPagamento forma;
    private BigDecimal valor;
    private BigDecimal valorRecebido;
    private BigDecimal troco;
    private StatusPagamento status;
    private LocalDateTime horario;

    public PagamentoDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public long getIdVenda() { return idVenda; }
    public void setIdVenda(long idVenda) { this.idVenda = idVenda; }

    public FormaPagamento getForma() { return forma; }
    public void setForma(FormaPagamento forma) { this.forma = forma; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public BigDecimal getValorRecebido() { return valorRecebido; }
    public void setValorRecebido(BigDecimal valorRecebido) { this.valorRecebido = valorRecebido; }

    public BigDecimal getTroco() { return troco; }
    public void setTroco(BigDecimal troco) { this.troco = troco; }

    public StatusPagamento getStatus() { return status; }
    public void setStatus(StatusPagamento status) { this.status = status; }

    public LocalDateTime getHorario() { return horario; }
    public void setHorario(LocalDateTime horario) { this.horario = horario; }
}
