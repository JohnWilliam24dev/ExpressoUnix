package com.johnwilliam.ExpressoUnix.Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.johnwilliam.ExpressoUnix.Enums.StatusVenda;

import jakarta.persistence.*;

/** Ato comercial: quem vendeu, quando e quanto. Agrupa 1..N passagens (que apontam para a venda). */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class VendaModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime horarioEmissao;

    @ManyToOne
    @JoinColumn(name = "id_funcionario", referencedColumnName = "id", insertable = false, updatable = false)
    private FuncionarioModels funcionario;
    @Column(name = "id_funcionario", nullable = false)
    private long idFuncionario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private StatusVenda status;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal descontoTotal = BigDecimal.ZERO;

    public VendaModels() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getHorarioEmissao() { return horarioEmissao; }
    public void setHorarioEmissao(LocalDateTime horarioEmissao) { this.horarioEmissao = horarioEmissao; }

    public long getIdFuncionario() { return idFuncionario; }
    public void setIdFuncionario(long idFuncionario) { this.idFuncionario = idFuncionario; }

    public FuncionarioModels getFuncionario() { return funcionario; }
    public void setFuncionario(FuncionarioModels funcionario) { this.funcionario = funcionario; }

    public StatusVenda getStatus() { return status; }
    public void setStatus(StatusVenda status) { this.status = status; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    public BigDecimal getDescontoTotal() { return descontoTotal; }
    public void setDescontoTotal(BigDecimal descontoTotal) { this.descontoTotal = descontoTotal; }
}
