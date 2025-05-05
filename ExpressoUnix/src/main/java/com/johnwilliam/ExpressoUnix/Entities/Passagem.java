package com.johnwilliam.ExpressoUnix.Entities;


import com.johnwilliam.ExpressoUnix.Enums.StatusPassagem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;




public class Passagem {


    private Long id;

   
    private StatusPassagem status;

    
    private Viagem viagem;
    
    private long idViagem;

   
    private Assento assento;
    
    private long idAssento;

   
    private Passageiro passageiro;
   
    private long idPassageiro;

    
    private LocalDate dataPassagem;

   
    private LocalTime horaPassagem;

   
    private String origem;


    private String destino;

    
    private BigDecimal distancia;

    
    private BigDecimal preco;


    private List<Venda> vendas;

    public Passagem() {}

    public Passagem(StatusPassagem status, Viagem viagem, Assento assento, Passageiro passageiro,
                    LocalDate dataPassagem, LocalTime horaPassagem, String origem, String destino,
                    BigDecimal distancia, BigDecimal preco) {
        this.status = status;
        this.viagem = viagem;
        this.idViagem = (viagem != null) ? viagem.getId() : 0;
        this.assento = assento;
        this.idAssento = (assento != null) ? assento.getId() : 0;
        this.passageiro = passageiro;
        this.idPassageiro = (passageiro != null) ? passageiro.getId() : 0;
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
