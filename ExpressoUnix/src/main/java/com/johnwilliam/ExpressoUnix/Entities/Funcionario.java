package com.johnwilliam.ExpressoUnix.Entities;



import java.time.LocalDate;
import java.util.List;

import com.johnwilliam.ExpressoUnix.Entities.Objects.*;



public class Funcionario {

    private long id;
    
    private Text nome;

    private Email email;
    
    private Telefone telefone;
    
    private CPF cpf;
    
    private DataNascimento dataNascimento;
    
    private Text cargo;
    
    private List<Venda> vendas;

    

    public Funcionario(String nome,String email,String telefone,String cpf, LocalDate dataNascimento, String cargo) {
        this.nome= new Text(nome);
        this.email= new Email(email);
        this.telefone=new Telefone(telefone);
        this.cpf=new CPF(cpf);
        this.dataNascimento = new DataNascimento(dataNascimento);
        this.cargo = new Text(cargo);
    }

    

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    
    
    

   
}
