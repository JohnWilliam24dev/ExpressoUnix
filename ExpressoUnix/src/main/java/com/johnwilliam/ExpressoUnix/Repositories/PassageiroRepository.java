package com.johnwilliam.ExpressoUnix.Repositories;

import java.util.List;


import org.springframework.stereotype.Repository;

import com.johnwilliam.ExpressoUnix.Exceptions.ResourceNotFoundException;
import com.johnwilliam.ExpressoUnix.Models.PassageiroModels;
import com.johnwilliam.ExpressoUnix.Repositories.JPA.PassageiroJPA;

@Repository
public class PassageiroRepository {
    private final PassageiroJPA passageiroJPA;

    public PassageiroRepository(PassageiroJPA passageiroJPA) {
        this.passageiroJPA = passageiroJPA;
    }
    
    public void createPassageiro(PassageiroModels passageiro) {
        passageiroJPA.save(passageiro);
    }
    
    public PassageiroModels getPassageiroById(long id) {
        return passageiroJPA.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Passageiro", id));
    }
    
    public List<PassageiroModels> getAllPassageiro() {
        return passageiroJPA.findAll();
    }
    
    public void updatePassageiro(PassageiroModels passageiro) {
        if (passageiro.getId() == null || !passageiroJPA.existsById(passageiro.getId())) {
            throw new ResourceNotFoundException("Passageiro", passageiro.getId());
        }
        passageiroJPA.save(passageiro);
    }
    
    public void deletePassageiro(long id) {
        passageiroJPA.deleteById(id);
    }
}
