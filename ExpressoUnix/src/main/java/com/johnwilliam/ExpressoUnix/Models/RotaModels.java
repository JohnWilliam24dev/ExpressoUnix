package com.johnwilliam.ExpressoUnix.Models;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"origem", "destino"})})
public class RotaModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String origem;

    @Column(nullable = false, length = 100)
    private String destino;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal distanciaKm;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precoBase;

    public RotaModels() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOrigem() { return origem; }
    public void setOrigem(String origem) { this.origem = origem; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public BigDecimal getDistanciaKm() { return distanciaKm; }
    public void setDistanciaKm(BigDecimal distanciaKm) { this.distanciaKm = distanciaKm; }

    public BigDecimal getPrecoBase() { return precoBase; }
    public void setPrecoBase(BigDecimal precoBase) { this.precoBase = precoBase; }
}
