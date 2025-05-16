package com.johnwilliam.ExpressoUnix.Entities;



import java.time.LocalDate;
import java.util.List;

import com.johnwilliam.ExpressoUnix.Entities.Objects.*;



public class Passageiro {
    
    private Long id;
    
    private Text nome;
    
    private Email email;
    
    private Telefone telefone;
    
    private CPF cpf;
    
    private DataNascimento dataNascimento;
    
    private List<Passagem> passagens;

    

    public Passageiro(String nome, String email, String telefone, String cpf, LocalDate dataNascimento) {
        this.nome.setText(cpf);
        this.email = new Email(email);
        this.telefone = new Telefone(telefone);
        this.cpf = new CPF(cpf);
        this.dataNascimento = new DataNascimento(dataNascimento);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() {
         return nome.toString(); 
        }
    public void setNome(String nome) {
         this.nome.setText(nome);  
        }

    public String getEmail() {
         return email.toString(); 
        }
    public void setEmail(String email) {
         this.email.setEmail(email); 
        }

    public String getTelefone() {
         return telefone.toString(); 
        }
    public void setTelefone(String telefone) {
         this.telefone.setTelefone(telefone); 
        }

    public String getCpf() {
         return cpf.toString(); 
        }
    public void setCpf(String cpf) {
         this.cpf.setCpf(cpf); 
        }

    public LocalDate getDataNascimento() {
         return dataNascimento.getDataNascimento(); 
        }
    public void setDataNascimento(LocalDate dataNascimento) {
         this.dataNascimento.setDataNascimento(dataNascimento);  
        }

    
}
