package com.johnwilliam.ExpressoUnix.DTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class RotaDTO {
    private Long id;

    @NotBlank(message = "origem e obrigatoria")
    @Size(max = 100, message = "origem deve ter no maximo 100 caracteres")
    private String origem;

    @NotBlank(message = "destino e obrigatorio")
    @Size(max = 100, message = "destino deve ter no maximo 100 caracteres")
    private String destino;

    @NotNull(message = "distanciaKm e obrigatoria")
    @Positive(message = "distanciaKm deve ser maior que zero")
    @Digits(integer = 8, fraction = 2, message = "distanciaKm aceita ate 8 inteiros e 2 decimais")
    private BigDecimal distanciaKm;

    @NotNull(message = "precoBase e obrigatorio")
    @Positive(message = "precoBase deve ser maior que zero")
    @Digits(integer = 8, fraction = 2, message = "precoBase aceita ate 8 inteiros e 2 decimais")
    private BigDecimal precoBase;

    public RotaDTO() {}

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
