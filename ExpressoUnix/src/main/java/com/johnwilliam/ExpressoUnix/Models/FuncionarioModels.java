package com.johnwilliam.ExpressoUnix.Models;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class FuncionarioModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    
    @Column(nullable = false)
    private String cargo;

    @OneToMany(mappedBy = "funcionario", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<VendaModels> vendas;

    public FuncionarioModels() {}

    public FuncionarioModels(String nome,String email,String telefone,String cpf, LocalDate dataNascimento, String cargo) {
        this.nome = nome;
        this.email=email;
        this.telefone=telefone;
        this.cpf=cpf;
        this.dataNascimento = dataNascimento;
        this.cargo = cargo;
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

    public List<VendaModels> getVendas() {
        return vendas;
    }

    public void setVendas(List<VendaModels> vendas) {
        this.vendas = vendas;
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
