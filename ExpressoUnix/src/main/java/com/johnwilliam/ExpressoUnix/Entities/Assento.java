package com.johnwilliam.ExpressoUnix.Entities;

import com.johnwilliam.ExpressoUnix.Enums.StatusAssento;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"id_viagem", "numeroAssento"})})
public class Assento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "id_viagem", nullable = false, referencedColumnName = "id",insertable = false, updatable = false)
    Viagem viagem;

    @Column(name="id_viagem")
    private long idViagem;

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
    public long getIdViagem() { //perceba que não há getters e setters para Viagem apenas para id viagem
        return idViagem;
    }
    public void setIdViagem(long idViagem) {
        this.idViagem = idViagem;
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
