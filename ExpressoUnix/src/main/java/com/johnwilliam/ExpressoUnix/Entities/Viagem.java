package com.johnwilliam.ExpressoUnix.Entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Viagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id; 

    @ManyToOne
    @JoinColumn(name = "id_veiculo", nullable = false)
    Veiculo veiculo;

    @Column(nullable = false)
    LocalDate dataViagem;

    @Column(nullable = false)
    LocalTime horaViagem;

    @Column(nullable = false, length = 100)
    String origem;

    @Column(nullable = false, length = 100)
    String destino;

    public Viagem() {}

    public Viagem(Veiculo veiculo, LocalDate dataViagem, LocalTime horaViagem, String origem, String destino) {
        this.veiculo = veiculo;
        this.dataViagem = dataViagem;
        this.horaViagem = horaViagem;
        this.origem = origem;
        this.destino = destino;
    }

    

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Veiculo getVeiculo() {
        return veiculo;
    }
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    public LocalDate getDataViagem() {
        return dataViagem;
    }
    public void setDataViagem(LocalDate dataViagem) {
        this.dataViagem = dataViagem;
    }
    public LocalTime getHoraViagem() {
        return horaViagem;
    }
    public void setHoraViagem(LocalTime horaViagem) {
        this.horaViagem = horaViagem;
    }
    public String getOrigem() {
        return origem;
    }
    public void setOrigem(String origem) {
        this.origem = origem;
    }
    public String getDestino() {
        return destino;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }
}
