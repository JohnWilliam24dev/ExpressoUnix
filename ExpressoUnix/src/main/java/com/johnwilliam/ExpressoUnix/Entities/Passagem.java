package com.johnwilliam.ExpressoUnix.Entities;

import jakarta.persistence.*;
import com.johnwilliam.ExpressoUnix.Enum.StatusPassagem;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"id_viagem", "id_assento"})})
public class Passagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id; 

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    StatusPassagem status;

    @ManyToOne
    @JoinColumn(name = "id_viagem", nullable = false)
    Viagem viagem;

    @ManyToOne
    @JoinColumn(name = "id_assento", nullable = false)
    Assento assento;

    @ManyToOne
    @JoinColumn(name = "id_passageiro", nullable = false)
    Passageiro passageiro;

    @Column(nullable = false)
    LocalDate dataPassagem;

    @Column(nullable = false)
    LocalTime horaPassagem;

    @Column(nullable = false, length = 100)
    String origem;

    @Column(nullable = false, length = 100)
    String destino;

    @Column(nullable = false, precision = 10, scale = 2)
    BigDecimal distancia;

    @Column(nullable = false, precision = 10, scale = 2)
    BigDecimal preco;

    public Passagem() {}

    public Passagem(StatusPassagem status, Viagem viagem, Assento assento, Passageiro passageiro,
                    LocalDate dataPassagem, LocalTime horaPassagem, String origem, String destino,
                    BigDecimal distancia, BigDecimal preco) {
        this.status = status;
        this.viagem = viagem;
        this.assento = assento;
        this.passageiro = passageiro;
        this.dataPassagem = dataPassagem;
        this.horaPassagem = horaPassagem;
        this.origem = origem;
        this.destino = destino;
        this.distancia = distancia;
        this.preco = preco;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public StatusPassagem getStatus() {
        return status;
    }
    public void setStatus(StatusPassagem status) {
        this.status = status;
    }
    public Viagem getViagem() {
        return viagem;
    }
    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }
    public Assento getAssento() {
        return assento;
    }
    public void setAssento(Assento assento) {
        this.assento = assento;
    }
    public Passageiro getPassageiro() {
        return passageiro;
    }
    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }
    public LocalDate getDataPassagem() {
        return dataPassagem;
    }
    public void setDataPassagem(LocalDate dataPassagem) {
        this.dataPassagem = dataPassagem;
    }
    public LocalTime getHoraPassagem() {
        return horaPassagem;
    }
    public void setHoraPassagem(LocalTime horaPassagem) {
        this.horaPassagem = horaPassagem;
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
    public BigDecimal getDistancia() {
        return distancia;
    }
    public void setDistancia(BigDecimal distancia) {
        this.distancia = distancia;
    }
    public BigDecimal getPreco() {
        return preco;
    }
    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
