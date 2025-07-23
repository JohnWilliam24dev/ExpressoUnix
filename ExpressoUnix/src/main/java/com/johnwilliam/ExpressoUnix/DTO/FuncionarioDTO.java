package com.johnwilliam.ExpressoUnix.DTO;

import java.time.LocalDate;
import java.util.List;

public class FuncionarioDTO {
    private long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private LocalDate dataNascimento;
    private String cargo;
    private List<VendaDTO> vendas;

    public FuncionarioDTO() {}

    public FuncionarioDTO(String nome, String email, String telefone, String cpf, LocalDate dataNascimento, String cargo) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.cargo = cargo;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public List<VendaDTO> getVendas() { return vendas; }
    public void setVendas(List<VendaDTO> vendas) { this.vendas = vendas; }
}
