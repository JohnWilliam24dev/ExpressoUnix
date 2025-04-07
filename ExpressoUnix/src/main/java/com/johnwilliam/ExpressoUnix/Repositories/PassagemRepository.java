package com.johnwilliam.ExpressoUnix.Repositories;

import java.util.List;


import org.springframework.stereotype.Repository;

import com.johnwilliam.ExpressoUnix.Entities.Passagem;
import com.johnwilliam.ExpressoUnix.Repositories.JPA.PassagemJPA;

@Repository
public class PassagemRepository {
    private final PassagemJPA passagemJPA;

    public PassagemRepository(PassagemJPA passagemJPA) {
        this.passagemJPA = passagemJPA;
    }
    
    public void createPassagem(Passagem passagem) {
        passagemJPA.save(passagem);
    }
    
    public Passagem getPassagemById(long id) {
        return passagemJPA.findById(id).get();
    }
    
    public List<Passagem> getAllPassagem() {
        return passagemJPA.findAll();
    }
    
    public void updatePassagem(Passagem passagem) {
        
        this.passagemJPA.save(passagem);
    }
    
    public void deletePassagem(long id) {
        passagemJPA.deleteById(id);
    }
}
