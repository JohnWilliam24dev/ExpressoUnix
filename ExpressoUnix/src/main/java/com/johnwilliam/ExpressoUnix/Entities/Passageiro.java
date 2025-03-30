package com.johnwilliam.ExpressoUnix.Entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Passageiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id; // mapeia id_passageiro

    @Column(nullable = false, length = 150)
    String nome;

    @Column(nullable = false, length = 150)
    String email;

    @Column(nullable = false, length = 15)
    String telefone;

    @Column(nullable = false, length = 11)
    String cpf;

    @Column(nullable = false)
    LocalDate dataNascimento;

    public Passageiro() {}

    public Passageiro(String nome, String email, String telefone, String cpf, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
