package com.johnwilliam.ExpressoUnix.DTO;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PassageiroDTO {
    private Long id;

    @NotBlank(message = "nome e obrigatorio")
    @Size(max = 150, message = "nome deve ter no maximo 150 caracteres")
    private String nome;

    @NotBlank(message = "email e obrigatorio")
    @Email(message = "email invalido")
    @Size(max = 150, message = "email deve ter no maximo 150 caracteres")
    private String email;

    @NotBlank(message = "telefone e obrigatorio")
    @Size(max = 15, message = "telefone deve ter no maximo 15 caracteres")
    private String telefone;

    @NotBlank(message = "cpf e obrigatorio")
    @Pattern(regexp = "\\d{11}", message = "cpf deve conter exatamente 11 digitos numericos")
    private String cpf;

    @NotNull(message = "dataNascimento e obrigatoria")
    @Past(message = "dataNascimento deve estar no passado")
    private LocalDate dataNascimento;

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
}
