package com.johnwilliam.ExpressoUnix.Entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id; 

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime horarioEmissao;

    @ManyToOne
    @JoinColumn(name = "id_funcionario", nullable = false)
    Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "id_passagem", nullable = false)
    Passagem passagem;

    public Venda() {}

    public Venda(Funcionario funcionario, Passagem passagem) {
        this.funcionario = funcionario;
        this.passagem = passagem;
    }

    

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getHorarioEmissao() {
        return horarioEmissao;
    }
    public void setHorarioEmissao(LocalDateTime horarioEmissao) {
        this.horarioEmissao = horarioEmissao;
    }
    public Funcionario getFuncionario() {
        return funcionario;
    }
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    public Passagem getPassagem() {
        return passagem;
    }
    public void setPassagem(Passagem passagem) {
        this.passagem = passagem;
    }
}
