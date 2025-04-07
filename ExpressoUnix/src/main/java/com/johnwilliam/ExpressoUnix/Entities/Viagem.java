package com.johnwilliam.ExpressoUnix.Entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Viagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_veiculo",  referencedColumnName = "id", insertable = false, updatable = false)
    private Veiculo veiculo;

    @Column(name = "id_veiculo", nullable = false)
    private long idVeiculo;

    @Column(nullable = false)
    private LocalDate dataViagem;

    @Column(nullable = false)
    private LocalTime horaViagem;

    @Column(nullable = false, length = 100)
    private String origem;

    @Column(nullable = false, length = 100)
    private String destino;

    public Viagem() {}

    public Viagem(Veiculo veiculo, LocalDate dataViagem, LocalTime horaViagem, String origem, String destino) {
        this.veiculo = veiculo;
        this.idVeiculo = (veiculo != null) ? veiculo.getId() : 0;
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

    // Apenas getter e setter para o identificador do veículo
    public long getIdVeiculo() {
        return idVeiculo;
    }
    public void setIdVeiculo(long idVeiculo) {
        this.idVeiculo = idVeiculo;
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
