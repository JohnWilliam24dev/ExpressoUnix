package com.johnwilliam.ExpressoUnix.Entities.Objects;

public class CPF {
    private String cpf;

    public CPF(String cpf) {
        this.cpf = cpf;
    }
    public String getValue(){
        return cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
