package com.johnwilliam.ExpressoUnix.Repositories;

import java.util.List;

import org.springframework.stereotype.Repository;


import com.johnwilliam.ExpressoUnix.Models.AssentoModels;
import com.johnwilliam.ExpressoUnix.Repositories.JPA.AssentoJPA;
@Repository
public class AssentoRepository {
    private final AssentoJPA assentoJPA;

    public AssentoRepository(AssentoJPA assentoJPA) {
        this.assentoJPA = assentoJPA;
    }
    public void createAssento(List<AssentoModels> assento){
        assentoJPA.saveAll(assento);
        
    }
    public void createAssento(AssentoModels assento){
        assentoJPA.save(assento);
        
    }
    public void createAllAssento(List<AssentoModels> lista_assentos){
        assentoJPA.saveAll(lista_assentos);
    }
    public AssentoModels getAssentoById(long id){
        return assentoJPA.findById(id).get();
    }
    public List<AssentoModels> getAllAssento(){
        return assentoJPA.findAll();
    }
    public void updateAssento( AssentoModels assento){
        this.assentoJPA.save(assento);
    }
    public void deleteAssento(long id){
        assentoJPA.deleteById(id);
    }
    
}
