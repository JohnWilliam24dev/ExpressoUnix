package com.johnwilliam.ExpressoUnix.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.johnwilliam.ExpressoUnix.Enums.StatusPassagem;
import com.johnwilliam.ExpressoUnix.Enums.TipoTarifa;
import com.johnwilliam.ExpressoUnix.Enums.TipoTrecho;

/** Somente saida: passagens so sao criadas dentro de uma venda (POST /venda). */
public class PassagemDTO {
    private Long id;
    private long idVenda;
    private TipoTrecho tipoTrecho;
    private Long idPassagemVinculada;
    private StatusPassagem status;
    private long idViagem;
    private long idAssento;
    private long idPassageiro;
    private LocalDate dataPassagem;
    private LocalTime horaPassagem;
    private String origem;
    private String destino;
    private BigDecimal distancia;
    private TipoTarifa tipoTarifa;
    private BigDecimal tarifaBase;
    private BigDecimal desconto;
    private BigDecimal valorPago;

    public PassagemDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public long getIdVenda() { return idVenda; }
    public void setIdVenda(long idVenda) { this.idVenda = idVenda; }

    public TipoTrecho getTipoTrecho() { return tipoTrecho; }
    public void setTipoTrecho(TipoTrecho tipoTrecho) { this.tipoTrecho = tipoTrecho; }

    public Long getIdPassagemVinculada() { return idPassagemVinculada; }
    public void setIdPassagemVinculada(Long idPassagemVinculada) { this.idPassagemVinculada = idPassagemVinculada; }

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

    public TipoTarifa getTipoTarifa() { return tipoTarifa; }
    public void setTipoTarifa(TipoTarifa tipoTarifa) { this.tipoTarifa = tipoTarifa; }

    public BigDecimal getTarifaBase() { return tarifaBase; }
    public void setTarifaBase(BigDecimal tarifaBase) { this.tarifaBase = tarifaBase; }

    public BigDecimal getDesconto() { return desconto; }
    public void setDesconto(BigDecimal desconto) { this.desconto = desconto; }

    public BigDecimal getValorPago() { return valorPago; }
    public void setValorPago(BigDecimal valorPago) { this.valorPago = valorPago; }
}
