package com.johnwilliam.ExpressoUnix.Models;

import java.math.BigDecimal;

import com.johnwilliam.ExpressoUnix.Enums.Classe;

import jakarta.persistence.*;

/** Multiplicador de preco por classe do veiculo (uma linha por classe). */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"classe"})})
public class TabelaPrecoModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Classe classe;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal multiplicador;

    public TabelaPrecoModels() {}

    public TabelaPrecoModels(Classe classe, BigDecimal multiplicador) {
        this.classe = classe;
        this.multiplicador = multiplicador;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Classe getClasse() { return classe; }
    public void setClasse(Classe classe) { this.classe = classe; }

    public BigDecimal getMultiplicador() { return multiplicador; }
    public void setMultiplicador(BigDecimal multiplicador) { this.multiplicador = multiplicador; }
}
