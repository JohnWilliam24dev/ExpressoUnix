package com.johnwilliam.ExpressoUnix.Entities;


import com.johnwilliam.ExpressoUnix.Entities.Objects.Text;
import com.johnwilliam.ExpressoUnix.Enums.StatusPassagem;
import com.johnwilliam.ExpressoUnix.Models.AssentoModels;
import com.johnwilliam.ExpressoUnix.DTO.ViagemDTO;
import com.johnwilliam.ExpressoUnix.DTO.AssentoDTO;
import com.johnwilliam.ExpressoUnix.DTO.PassageiroDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
public class Passagem {

    private Long id;
   
    private StatusPassagem status;
    
    private ViagemDTO viagem;
   
    private AssentoDTO assento;
   
    private PassageiroDTO passageiro;
   
    private LocalDate dataPassagem;
   
    private LocalTime horaPassagem;
   
    private Text origem;

    private Text destino;
    
    private BigDecimal distancia;
    
    private BigDecimal preco;

    private List<Venda> vendas;

    

    public Passagem(long id, StatusPassagem status, ViagemDTO viagem, AssentoDTO assento, PassageiroDTO passageiro,
                    LocalDate dataPassagem, LocalTime horaPassagem, String origem, String destino,
                    BigDecimal distancia, BigDecimal preco) {
        this.status = status;
        this.viagem = viagem;
        this.assento = assento;
        this.passageiro = passageiro;
        this.dataPassagem = dataPassagem;
        this.horaPassagem = horaPassagem;
        this.origem = new Text(origem);
        this.destino = new Text(destino);
        this.distancia = distancia;
        this.preco = preco;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public StatusPassagem getStatus() { return status; }
    public void setStatus(StatusPassagem status) { this.status = status; }

    public LocalDate getDataPassagem() { return dataPassagem; }
    public void setDataPassagem(LocalDate dataPassagem) { this.dataPassagem = dataPassagem; }

    public LocalTime getHoraPassagem() { return horaPassagem; }
    public void setHoraPassagem(LocalTime horaPassagem) { this.horaPassagem = horaPassagem; }

    public Text getOrigem() { return origem; }
    public void setOrigem(String origem) { this.origem.setText(origem);; }

    public Text getDestino() { return destino; }
    public void setDestino(String destino) { this.destino.setText(destino); }

    public BigDecimal getDistancia() { return distancia; }
    public void setDistancia(BigDecimal distancia) { this.distancia = distancia; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }



    public ViagemDTO getViagem() {
        return viagem;
    }

    public void setViagem(ViagemDTO viagem) {
        this.viagem = viagem;
    }

    public AssentoDTO getAssento() {
        return assento;
    }

    public void setAssento(AssentoDTO assento) {
        this.assento = assento;
    }

    public PassageiroDTO getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(PassageiroDTO passageiro) {
        this.passageiro = passageiro;
    }

    public void setOrigem(Text origem) {
        this.origem = origem;
    }

    public void setDestino(Text destino) {
        this.destino = destino;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

}
