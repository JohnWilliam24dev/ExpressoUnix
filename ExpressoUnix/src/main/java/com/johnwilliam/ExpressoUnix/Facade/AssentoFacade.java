package com.johnwilliam.ExpressoUnix.Facade;

import java.util.List;
import org.springframework.stereotype.Component;
import com.johnwilliam.ExpressoUnix.Applications.AssentoApplication;

import com.johnwilliam.ExpressoUnix.Models.AssentoModels;

@Component
public class AssentoFacade {
    private final AssentoApplication assentoApplication;
    
    public AssentoFacade(AssentoApplication assentoApplication) {
        this.assentoApplication = assentoApplication;
    }
    
    public void createAssento(AssentoModels assento) {
        assentoApplication.createAssento(assento);
    }
    
    public AssentoModels getAssentoById(long id) {
        return assentoApplication.getAssentoById(id);
    }
    
    public List<AssentoModels> getAllAssento() {
        return assentoApplication.getAllAssento();
    }
    
    public void updateAssento( AssentoModels assento) {
        assentoApplication.updateAssento( assento);
    }
    
    public void deleteAssento(long id) {
        assentoApplication.deleteAssento(id);
    }
}
