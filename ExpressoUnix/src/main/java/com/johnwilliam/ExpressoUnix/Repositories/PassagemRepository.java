package com.johnwilliam.ExpressoUnix.Repositories;

import java.util.List;


import org.springframework.stereotype.Repository;


import com.johnwilliam.ExpressoUnix.Models.PassagemModels;
import com.johnwilliam.ExpressoUnix.Repositories.JPA.PassagemJPA;

@Repository
public class PassagemRepository {
    private final PassagemJPA passagemJPA;

    public PassagemRepository(PassagemJPA passagemJPA) {
        this.passagemJPA = passagemJPA;
    }
    
    public void createPassagem(PassagemModels passagem) {
        passagemJPA.save(passagem);
    }
    
    public PassagemModels getPassagemById(long id) {
        return passagemJPA.findById(id).get();
    }
    
    public List<PassagemModels> getAllPassagem() {
        return passagemJPA.findAll();
    }
    
    public void updatePassagem(PassagemModels passagem) {
        
        this.passagemJPA.save(passagem);
    }
    
    public void deletePassagem(long id) {
        passagemJPA.deleteById(id);
    }
}
