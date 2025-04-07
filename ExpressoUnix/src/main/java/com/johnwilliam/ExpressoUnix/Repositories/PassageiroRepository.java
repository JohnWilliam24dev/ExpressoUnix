package com.johnwilliam.ExpressoUnix.Repositories;

import java.util.List;


import org.springframework.stereotype.Repository;

import com.johnwilliam.ExpressoUnix.Entities.Passageiro;
import com.johnwilliam.ExpressoUnix.Repositories.JPA.PassageiroJPA;

@Repository
public class PassageiroRepository {
    private final PassageiroJPA passageiroJPA;

    public PassageiroRepository(PassageiroJPA passageiroJPA) {
        this.passageiroJPA = passageiroJPA;
    }
    
    public void createPassageiro(Passageiro passageiro) {
        passageiroJPA.save(passageiro);
    }
    
    public Passageiro getPassageiroById(long id) {
        return passageiroJPA.findById(id).get();
    }
    
    public List<Passageiro> getAllPassageiro() {
        return passageiroJPA.findAll();
    }
    
    public void updatePassageiro( Passageiro passageiro) {
        
        
        this.passageiroJPA.save(passageiro);
    }
    
    public void deletePassageiro(long id) {
        passageiroJPA.deleteById(id);
    }
}
