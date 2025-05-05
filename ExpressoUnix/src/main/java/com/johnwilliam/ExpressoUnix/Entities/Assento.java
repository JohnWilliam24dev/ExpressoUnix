package com.johnwilliam.ExpressoUnix.Entities;


import com.johnwilliam.ExpressoUnix.Enums.StatusAssento;
import java.util.List;



public class Assento {

    private Long id;

    private Viagem viagem;
    
    private long idViagem;
    
    private int numeroAssento;
    
    private StatusAssento statusAssento = StatusAssento.Livre;
    
    private List<Passagem> passagens;

    public Assento() {}

    public Assento(Viagem viagem, int numeroAssento, StatusAssento statusAssento) {
        this.viagem = viagem;
        this.idViagem = (viagem != null) ? viagem.getId() : 0;
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

    public List<Passagem> getPassagens() { return passagens; }
    public void setPassagens(List<Passagem> passagens) { this.passagens = passagens; }
}
