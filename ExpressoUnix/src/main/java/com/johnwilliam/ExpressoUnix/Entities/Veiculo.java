package com.johnwilliam.ExpressoUnix.Entities;

import java.util.List;
import com.johnwilliam.ExpressoUnix.Enums.Classe;
import com.johnwilliam.ExpressoUnix.Enums.StatusVeiculo;

public class Veiculo {
    
    private Long id;
    
    private Classe classe;
    
    private int capacidade;
    
    
    private StatusVeiculo statusVeiculo = StatusVeiculo.Disponivel;
    
    private List<Viagem> viagens;

    public Veiculo(){}

    public Veiculo(long id,Classe classe, int capacidade, StatusVeiculo statusVeiculo) {
        this.id=id;
        this.classe = classe;
        this.capacidade = capacidade;
        this.statusVeiculo = statusVeiculo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Viagem> getViagens() {
        return viagens;
    }

    public void setViagens(List<Viagem> viagens) {
        this.viagens = viagens;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
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

    public void verifyCapacity(){
        if (capacidade>60 || capacidade<2) {
            throw new IllegalArgumentException("A capacidade deve ser no maximo 60 assentos");
        } 
    }
    public int getCapacidade() {
        return capacidade;
    }

    
    
}
