package com.johnwilliam.ExpressoUnix.Entities;


import jakarta.persistence.*;
import java.util.List;
import com.johnwilliam.ExpressoUnix.Enums.Classe;
import com.johnwilliam.ExpressoUnix.Enums.StatusVeiculo;

@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Classe classe;

    @Column(nullable = false)
    private int capacidade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusVeiculo statusVeiculo = StatusVeiculo.Disponivel;

    @OneToMany(mappedBy = "veiculo", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Viagem> viagens;

    public Veiculo() {}

    public Veiculo(Classe classe, int capacidade, StatusVeiculo statusVeiculo) {
        this.classe = classe;
        this.capacidade = capacidade;
        this.statusVeiculo = statusVeiculo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Classe getClasse() { return classe; }
    public void setClasse(Classe classe) { this.classe = classe; }

    public int getCapacidade() { return capacidade; }
    public void setCapacidade(int capacidade) { this.capacidade = capacidade; }

    public StatusVeiculo getStatusVeiculo() { return statusVeiculo; }
    public void setStatusVeiculo(StatusVeiculo statusVeiculo) { this.statusVeiculo = statusVeiculo; }

    
}
