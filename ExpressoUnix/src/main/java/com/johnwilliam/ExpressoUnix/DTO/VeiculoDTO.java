package com.johnwilliam.ExpressoUnix.DTO;

import java.util.List;
import com.johnwilliam.ExpressoUnix.Enums.Classe;
import com.johnwilliam.ExpressoUnix.Enums.StatusVeiculo;

public class VeiculoDTO {
    private Long id;
    private Classe classe;
    private int capacidade;
    private StatusVeiculo statusVeiculo = StatusVeiculo.Disponivel;
    private List<ViagemDTO> viagens;

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

    public List<ViagemDTO> getViagens() { return viagens; }
    public void setViagens(List<ViagemDTO> viagens) { this.viagens = viagens; }
}
