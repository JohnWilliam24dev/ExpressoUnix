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

    public Text getNome() {
        return nome;
    }
    public void setNome(Text nome) {
        this.nome = nome;
    }
    public Email getEmail() {
        return email;
    }
    public void setEmail(Email email) {
        this.email = email;
    }
    public Telefone getTelefone() {
        return telefone;
    }
    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }
    public CPF getCpf() {
        return cpf;
    }
    public void setCpf(CPF cpf) {
        this.cpf = cpf;
    }
    public DataNascimento getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(DataNascimento dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public Text getCargo() {
        return cargo;
    }
    public void setCargo(Text cargo) {
        this.cargo = cargo;
    }
    public List<Venda> getVendas() {
        return vendas;
    }
    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }
    
    
    

   
}
