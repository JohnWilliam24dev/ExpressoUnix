package com.johnwilliam.ExpressoUnix.Models;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class FuncionarioModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private String cargo;

    @OneToMany(mappedBy = "funcionario", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<VendaModels> vendas;

    public FuncionarioModels() {}

    public FuncionarioModels(String nome, LocalDate dataNascimento, String cargo) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cargo = cargo;
    }

    public FuncionarioModels(long id, String nome, LocalDate dataNascimento, String cargo) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cargo = cargo;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

   
}
