package com.johnwilliam.ExpressoUnix.DTO;

import com.johnwilliam.ExpressoUnix.Enums.StatusAssento;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AssentoDTO {
    private Long id;

    @Positive(message = "idViagem deve ser informado")
    private long idViagem;

    @Positive(message = "numeroAssento deve ser maior que zero")
    private int numeroAssento;

    @NotNull(message = "statusAssento nao pode ser nulo")
    private StatusAssento statusAssento = StatusAssento.Livre;

    public AssentoDTO() {}

    public AssentoDTO(long idViagem, int numeroAssento, StatusAssento statusAssento) {
        this.idViagem = idViagem;
        this.numeroAssento = numeroAssento;
        this.statusAssento = statusAssento;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public long getIdViagem() { return idViagem; }
    public void setIdViagem(long idViagem) { this.idViagem = idViagem; }

    public int getNumeroAssento() { return numeroAssento; }
    public void setNumeroAssento(int numeroAssento) { this.numeroAssento = numeroAssento; }

    public StatusAssento getStatusAssento() { return statusAssento; }
    public void setStatusAssento(StatusAssento statusAssento) { this.statusAssento = statusAssento; }
}
