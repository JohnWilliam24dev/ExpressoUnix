package com.johnwilliam.ExpressoUnix.Models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.johnwilliam.ExpressoUnix.Enums.StatusAssento;
import jakarta.persistence.*;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"id_viagem", "numeroAssento"})})
public class AssentoModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_viagem", nullable = false, referencedColumnName = "id", insertable = false, updatable = false)
    private ViagemModels viagem;

    @Column(name="id_viagem")
    private long idViagem;

    @Column(nullable = false)
    private int numeroAssento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAssento statusAssento = StatusAssento.Livre;

    @OneToMany(mappedBy = "assento", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<PassagemModels> passagens;

    public AssentoModels() {}

    public AssentoModels(ViagemModels viagem, int numeroAssento, StatusAssento statusAssento) {
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

    public List<PassagemModels> getPassagens() { return passagens; }
    public void setPassagens(List<PassagemModels> passagens) { this.passagens = passagens; }

    public ViagemModels getViagem() {
        return viagem;
    }

    public void setViagem(ViagemModels viagem) {
        this.viagem = viagem;
    }
    
}
