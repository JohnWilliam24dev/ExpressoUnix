package com.johnwilliam.ExpressoUnix.DTO;

import java.time.LocalDate;
import java.util.List;

public class PassageiroDTO {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private LocalDate dataNascimento;
    private List<PassagemDTO> passagens;

    public PassageiroDTO() {}

    public PassageiroDTO(String nome, String email, String telefone, String cpf, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public List<PassagemDTO> getPassagens() { return passagens; }
    public void setPassagens(List<PassagemDTO> passagens) { this.passagens = passagens; }
}
