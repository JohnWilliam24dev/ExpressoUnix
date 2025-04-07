package com.johnwilliam.ExpressoUnix.Repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.johnwilliam.ExpressoUnix.Entities.Assento;
import com.johnwilliam.ExpressoUnix.Repositories.JPA.AssentoJPA;
@Repository
public class AssentoRepository {
    private final AssentoJPA assentoJPA;

    public AssentoRepository(AssentoJPA assentoJPA) {
        this.assentoJPA = assentoJPA;
    }
    public void createAssento(Assento assento){
        assentoJPA.save(assento);
    }
    public Assento getAssentoById(long id){
        return assentoJPA.findById(id).get();
    }
    public List<Assento> getAllAssento(){
        return assentoJPA.findAll();
    }
    public void updateAssento( Assento assento){
        this.assentoJPA.save(assento);
    }
    public void deleteAssento(long id){
        assentoJPA.deleteById(id);
    }
    
}
