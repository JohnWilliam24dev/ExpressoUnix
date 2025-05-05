package com.johnwilliam.ExpressoUnix.Repositories;

import java.util.List;


import org.springframework.stereotype.Repository;

import com.johnwilliam.ExpressoUnix.Models.ViagemModels;
import com.johnwilliam.ExpressoUnix.Repositories.JPA.ViagemJPA;

@Repository
public class ViagemRepository {
    private final ViagemJPA viagemJPA;

    public ViagemRepository(ViagemJPA viagemJPA) {
        this.viagemJPA = viagemJPA;
    }
    
    public void createViagem(ViagemModels viagem) {
        viagemJPA.save(viagem);
    }
    
    public ViagemModels getViagemById(long id) {
        return viagemJPA.findById(id).get();
    }
    
    public List<ViagemModels> getAllViagem() {
        return viagemJPA.findAll();
    }
    
    public void updateViagem( ViagemModels viagem) {
        
        this.viagemJPA.save(viagem);
    }
    
    public void deleteViagem(long id) {
        viagemJPA.deleteById(id);
    }
}
