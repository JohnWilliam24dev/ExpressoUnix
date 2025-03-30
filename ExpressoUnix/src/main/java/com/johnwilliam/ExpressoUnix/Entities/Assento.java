package com.johnwilliam.ExpressoUnix.Entities;

import jakarta.persistence.*;
import com.johnwilliam.ExpressoUnix.Enum.StatusAssento;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"id_viagem", "numeroAssento"})})
public class Assento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id; 

    @ManyToOne
    @JoinColumn(name = "id_viagem", nullable = false)
    Viagem viagem;

    @Column(nullable = false)
    int numeroAssento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    StatusAssento statusAssento = StatusAssento.Livre;

    public Assento() {}

    public Assento(Viagem viagem, int numeroAssento, StatusAssento statusAssento) {
        this.viagem = viagem;
        this.numeroAssento = numeroAssento;
        this.statusAssento = statusAssento;
    }

   

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Viagem getViagem() {
        return viagem;
    }
    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }
    public int getNumeroAssento() {
        return numeroAssento;
    }
    public void setNumeroAssento(int numeroAssento) {
        this.numeroAssento = numeroAssento;
    }
    public StatusAssento getStatusAssento() {
        return statusAssento;
    }
    public void setStatusAssento(StatusAssento statusAssento) {
        this.statusAssento = statusAssento;
    }
}
