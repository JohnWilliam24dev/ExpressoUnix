package com.johnwilliam.ExpressoUnix.Entities;
import com.johnwilliam.ExpressoUnix.Enum.Classe;
import com.johnwilliam.ExpressoUnix.Enum.StatusVeiculo;

import jakarta.persistence.*;

@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Classe classe;

    @Column(nullable = false)
    int capacidade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    StatusVeiculo statusVeiculo = StatusVeiculo.Disponivel;

    public Veiculo() {}

    public Veiculo(Classe classe, int capacidade, StatusVeiculo statusVeiculo) {
        this.classe = classe;
        this.capacidade = capacidade;
        this.statusVeiculo = statusVeiculo;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Classe getClasse() {
        return classe;
    }
    public void setClasse(Classe classe) {
        this.classe = classe;
    }
    public int getCapacidade() {
        return capacidade;
    }
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
    public StatusVeiculo getStatusVeiculo() {
        return statusVeiculo;
    }
    public void setStatusVeiculo(StatusVeiculo statusVeiculo) {
        this.statusVeiculo = statusVeiculo;
    }
}
