package com.johnwilliam.ExpressoUnix.Facade;

import java.util.List;
import org.springframework.stereotype.Component;
import com.johnwilliam.ExpressoUnix.Applications.AssentoApplication;
import com.johnwilliam.ExpressoUnix.Entities.Assento;

@Component
public class AssentoFacade {
    private final AssentoApplication assentoApplication;
    
    public AssentoFacade(AssentoApplication assentoApplication) {
        this.assentoApplication = assentoApplication;
    }
    
    public void createAssento(Assento assento) {
        assentoApplication.createAssento(assento);
    }
    
    public Assento getAssentoById(long id) {
        return assentoApplication.getAssentoById(id);
    }
    
    public List<Assento> getAllAssento() {
        return assentoApplication.getAllAssento();
    }
    
    public void updateAssento( Assento assento) {
        assentoApplication.updateAssento( assento);
    }
    
    public void deleteAssento(long id) {
        assentoApplication.deleteAssento(id);
    }
}
