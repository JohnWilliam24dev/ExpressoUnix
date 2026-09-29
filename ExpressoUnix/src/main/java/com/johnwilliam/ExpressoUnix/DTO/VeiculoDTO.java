package com.johnwilliam.ExpressoUnix.DTO;

import com.johnwilliam.ExpressoUnix.Enums.Classe;
import com.johnwilliam.ExpressoUnix.Enums.StatusVeiculo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class VeiculoDTO {
    private Long id;

    @NotNull(message = "classe e obrigatoria")
    private Classe classe;

    @Min(value = 2, message = "capacidade minima e 2 assentos")
    @Max(value = 60, message = "capacidade maxima e 60 assentos")
    private int capacidade;

    @NotNull(message = "statusVeiculo nao pode ser nulo")
    private StatusVeiculo statusVeiculo = StatusVeiculo.Disponivel;

    public VeiculoDTO() {}

    public VeiculoDTO(Classe classe, int capacidade, StatusVeiculo statusVeiculo) {
        this.classe = classe;
        this.capacidade = capacidade;
        this.statusVeiculo = statusVeiculo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Classe getClasse() { return classe; }
    public void setClasse(Classe classe) { this.classe = classe; }

    public int getCapacidade() { return capacidade; }
    public void setCapacidade(int capacidade) { this.capacidade = capacidade; }

    public StatusVeiculo getStatusVeiculo() { return statusVeiculo; }
    public void setStatusVeiculo(StatusVeiculo statusVeiculo) { this.statusVeiculo = statusVeiculo; }
}
