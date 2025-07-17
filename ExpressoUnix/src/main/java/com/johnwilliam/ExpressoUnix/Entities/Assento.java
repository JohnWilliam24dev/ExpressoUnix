package com.johnwilliam.ExpressoUnix.Entities;


import com.johnwilliam.ExpressoUnix.Enums.StatusAssento;
import java.util.List;



public class Assento {

    private Long id;

    private long viagem;
    
    private int numeroAssento;
    
    private StatusAssento statusAssento = StatusAssento.Livre;
    
    private List<Passagem> passagens;

    public Assento(long id, long viagem, int numeroAssento, StatusAssento statusAssento) {
        this.viagem = viagem;
        this.numeroAssento = numeroAssento;
        this.statusAssento = statusAssento;
    }

    public Long getId() { return id; }
    public void setId(Long id) {
        this.id = id;
    }
    

    public int getNumeroAssento() { return numeroAssento; }
    public void setNumeroAssento(int numeroAssento) { this.numeroAssento = numeroAssento; }

    public StatusAssento getStatusAssento() { return statusAssento; }
    public void setStatusAssento(StatusAssento statusAssento) { this.statusAssento = statusAssento; }
    
    

    public long getViagem() {
        return viagem;
    }

    public void setViagem(long viagem) {
        this.viagem = viagem;
    }

    public List<Passagem> getPassagens() {
        return passagens;
    }

    public void setPassagens(List<Passagem> passagens) {
        this.passagens = passagens;
    }
    
}
