package com.johnwilliam.ExpressoUnix.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ViagemDTO {
    private Long id;

    @Positive(message = "idVeiculo deve ser informado")
    private long idVeiculo;

    @NotNull(message = "dataViagem e obrigatoria")
    private LocalDate dataViagem;

    @NotNull(message = "horaViagem e obrigatoria")
    private LocalTime horaViagem;

    @NotBlank(message = "origem e obrigatoria")
    @Size(max = 100, message = "origem deve ter no maximo 100 caracteres")
    private String origem;

    @NotBlank(message = "destino e obrigatorio")
    @Size(max = 100, message = "destino deve ter no maximo 100 caracteres")
    private String destino;

    public ViagemDTO() {}

    public ViagemDTO(long idVeiculo, LocalDate dataViagem, LocalTime horaViagem, String origem, String destino) {
        this.idVeiculo = idVeiculo;
        this.dataViagem = dataViagem;
        this.horaViagem = horaViagem;
        this.origem = origem;
        this.destino = destino;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public long getIdVeiculo() { return idVeiculo; }
    public void setIdVeiculo(long idVeiculo) { this.idVeiculo = idVeiculo; }

    public LocalDate getDataViagem() { return dataViagem; }
    public void setDataViagem(LocalDate dataViagem) { this.dataViagem = dataViagem; }

    public LocalTime getHoraViagem() { return horaViagem; }
    public void setHoraViagem(LocalTime horaViagem) { this.horaViagem = horaViagem; }

    public String getOrigem() { return origem; }
    public void setOrigem(String origem) { this.origem = origem; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }
}
