package com.johnwilliam.ExpressoUnix.Entities;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private String cargo;

    public Funcionario() {}

    public Funcionario(String nome, LocalDate dataNascimento, String cargo) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cargo = cargo;
    }

    public Funcionario(long id, String nome, LocalDate dataNascimento, String cargo) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cargo = cargo;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
