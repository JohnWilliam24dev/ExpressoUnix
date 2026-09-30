package com.johnwilliam.ExpressoUnix.Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.johnwilliam.ExpressoUnix.Enums.FormaPagamento;
import com.johnwilliam.ExpressoUnix.Enums.StatusPagamento;

import jakarta.persistence.*;

@Entity
public class PagamentoModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_venda", referencedColumnName = "id", insertable = false, updatable = false)
    private VendaModels venda;
    @Column(name = "id_venda", nullable = false)
    private long idVenda;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private FormaPagamento forma;

    /** Valor aplicado a venda. */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    /** Somente DINHEIRO: quanto o cliente entregou. */
    @Column(precision = 10, scale = 2)
    private BigDecimal valorRecebido;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal troco = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusPagamento status;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime horario;

    public PagamentoModels() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public VendaModels getVenda() { return venda; }
    public void setVenda(VendaModels venda) { this.venda = venda; }

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
