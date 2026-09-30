package com.johnwilliam.ExpressoUnix.DTO;

import java.math.BigDecimal;

import com.johnwilliam.ExpressoUnix.Enums.Classe;
import com.johnwilliam.ExpressoUnix.Enums.TipoTarifa;

/** Resultado do calculo de preco de uma viagem (somente saida). */
public class CotacaoDTO {
    private long idViagem;
    private String origem;
    private String destino;
    private Classe classe;
    private TipoTarifa tipoTarifa;
    private BigDecimal distanciaKm;
    private BigDecimal precoBase;
    private BigDecimal multiplicadorClasse;
    private BigDecimal percentualTarifa;
    /** Tarifa cheia (precoBase x multiplicador), antes do desconto da tarifa. */
    private BigDecimal tarifaBase;
    private BigDecimal desconto;
    /** Valor final a pagar (tarifaBase - desconto). */
    private BigDecimal preco;

    public CotacaoDTO() {}

    public long getIdViagem() { return idViagem; }
    public void setIdViagem(long idViagem) { this.idViagem = idViagem; }

    public String getOrigem() { return origem; }
    public void setOrigem(String origem) { this.origem = origem; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public Classe getClasse() { return classe; }
    public void setClasse(Classe classe) { this.classe = classe; }

    public TipoTarifa getTipoTarifa() { return tipoTarifa; }
    public void setTipoTarifa(TipoTarifa tipoTarifa) { this.tipoTarifa = tipoTarifa; }

    public BigDecimal getDistanciaKm() { return distanciaKm; }
    public void setDistanciaKm(BigDecimal distanciaKm) { this.distanciaKm = distanciaKm; }

    public BigDecimal getPrecoBase() { return precoBase; }
    public void setPrecoBase(BigDecimal precoBase) { this.precoBase = precoBase; }

    public BigDecimal getMultiplicadorClasse() { return multiplicadorClasse; }
    public void setMultiplicadorClasse(BigDecimal multiplicadorClasse) { this.multiplicadorClasse = multiplicadorClasse; }

    public BigDecimal getPercentualTarifa() { return percentualTarifa; }
    public void setPercentualTarifa(BigDecimal percentualTarifa) { this.percentualTarifa = percentualTarifa; }

    public BigDecimal getTarifaBase() { return tarifaBase; }
    public void setTarifaBase(BigDecimal tarifaBase) { this.tarifaBase = tarifaBase; }

    public BigDecimal getDesconto() { return desconto; }
    public void setDesconto(BigDecimal desconto) { this.desconto = desconto; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
}
