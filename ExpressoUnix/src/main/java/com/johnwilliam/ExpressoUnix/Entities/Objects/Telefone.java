package com.johnwilliam.ExpressoUnix.Entities.Objects;

public class Telefone {
    private String telefone;
    public Telefone(String telefone){
        this.telefone=telefone;
        
    }
    public String getValue(){
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
