package com.johnwilliam.ExpressoUnix.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.johnwilliam.ExpressoUnix.Enums.StatusPassagem;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class PassagemDTO {
    private Long id;

    @NotNull(message = "status e obrigatorio")
    private StatusPassagem status;

    @Positive(message = "idViagem deve ser informado")
    private long idViagem;

    @Positive(message = "idAssento deve ser informado")
    private long idAssento;

    @Positive(message = "idPassageiro deve ser informado")
    private long idPassageiro;

    @NotNull(message = "dataPassagem e obrigatoria")
    private LocalDate dataPassagem;

    @NotNull(message = "horaPassagem e obrigatoria")
    private LocalTime horaPassagem;

    @NotBlank(message = "origem e obrigatoria")
    @Size(max = 100, message = "origem deve ter no maximo 100 caracteres")
    private String origem;

    @NotBlank(message = "destino e obrigatorio")
    @Size(max = 100, message = "destino deve ter no maximo 100 caracteres")
    private String destino;

    @NotNull(message = "distancia e obrigatoria")
    @Positive(message = "distancia deve ser maior que zero")
    @Digits(integer = 8, fraction = 2, message = "distancia aceita ate 8 inteiros e 2 decimais")
    private BigDecimal distancia;

    @NotNull(message = "preco e obrigatorio")
    @PositiveOrZero(message = "preco nao pode ser negativo")
    @Digits(integer = 8, fraction = 2, message = "preco aceita ate 8 inteiros e 2 decimais")
    private BigDecimal preco;

    public PassagemDTO() {}

    public PassagemDTO(StatusPassagem status, long idViagem, long idAssento, long idPassageiro,
                       LocalDate dataPassagem, LocalTime horaPassagem, String origem, String destino,
                       BigDecimal distancia, BigDecimal preco) {
        this.status = status;
        this.idViagem = idViagem;
        this.idAssento = idAssento;
        this.idPassageiro = idPassageiro;
        this.dataPassagem = dataPassagem;
        this.horaPassagem = horaPassagem;
        this.origem = origem;
        this.destino = destino;
        this.distancia = distancia;
        this.preco = preco;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public StatusPassagem getStatus() { return status; }
    public void setStatus(StatusPassagem status) { this.status = status; }

    public long getIdViagem() { return idViagem; }
    public void setIdViagem(long idViagem) { this.idViagem = idViagem; }

    public long getIdAssento() { return idAssento; }
    public void setIdAssento(long idAssento) { this.idAssento = idAssento; }

    public long getIdPassageiro() { return idPassageiro; }
    public void setIdPassageiro(long idPassageiro) { this.idPassageiro = idPassageiro; }

    public LocalDate getDataPassagem() { return dataPassagem; }
    public void setDataPassagem(LocalDate dataPassagem) { this.dataPassagem = dataPassagem; }

    public LocalTime getHoraPassagem() { return horaPassagem; }
    public void setHoraPassagem(LocalTime horaPassagem) { this.horaPassagem = horaPassagem; }

    public String getOrigem() { return origem; }
    public void setOrigem(String origem) { this.origem = origem; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public BigDecimal getDistancia() { return distancia; }
    public void setDistancia(BigDecimal distancia) { this.distancia = distancia; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
}
