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

    

    public Passageiro(long id,String nome, String email, String telefone, String cpf, LocalDate dataNascimento) {
        this.nome= new Text(nome);
        this.email = new Email(email);
        this.telefone = new Telefone(telefone);
        this.cpf = new CPF(cpf);
        this.dataNascimento = new DataNascimento(dataNascimento);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Text getNome() {
         return nome; 
        }
    public void setNome(String nome) {
         this.nome.setText(nome);  
        }

    public Email getEmail() {
         return email; 
        }
    public void setEmail(String email) {
         this.email.setEmail(email); 
        }

    public Telefone getTelefone() {
         return telefone; 
        }
    public void setTelefone(String telefone) {
         this.telefone.setTelefone(telefone); 
        }

    public CPF getCpf() {
         return cpf; 
        }
    public void setCpf(String cpf) {
         this.cpf.setCpf(cpf); 
        }

    public DataNascimento getDataNascimento() {
         return dataNascimento; 
        }
    public void setDataNascimento(LocalDate dataNascimento) {
         this.dataNascimento.setDataNascimento(dataNascimento);  
        }

    
}
