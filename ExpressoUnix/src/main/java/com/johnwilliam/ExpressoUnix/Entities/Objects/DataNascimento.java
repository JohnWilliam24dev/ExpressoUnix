package com.johnwilliam.ExpressoUnix.Entities.Objects;

import java.time.LocalDate;

public class DataNascimento {
    private LocalDate dataNascimento;
    public DataNascimento(LocalDate dataNascimento){
        this.dataNascimento=dataNascimento;

    } 
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
