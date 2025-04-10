package com.johnwilliam.ExpressoUnix.Entities;


import com.johnwilliam.ExpressoUnix.Enums.StatusAssento;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"id_viagem", "numeroAssento"})})

public class Assento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_viagem", nullable = false, referencedColumnName = "id", insertable = false, updatable = false)
    private Viagem viagem;

    @Column(name="id_viagem")
    private long idViagem;

    @Column(nullable = false)
    private int numeroAssento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAssento statusAssento = StatusAssento.Livre;

    @OneToMany(mappedBy = "assento", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
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
