package com.johnwilliam.ExpressoUnix.Facade;

import java.util.List;
import org.springframework.stereotype.Component;
import com.johnwilliam.ExpressoUnix.Applications.AssentoApplication;
import com.johnwilliam.ExpressoUnix.DTO.AssentoDTO;


@Component
public class AssentoFacade {
    private final AssentoApplication assentoApplication;
    
    public AssentoFacade(AssentoApplication assentoApplication) {
        this.assentoApplication = assentoApplication;
    }
    
    public void createAssento(AssentoDTO assento) {
        assentoApplication.createAssento(assento);
    }
    
    public AssentoDTO getAssentoById(long id) {
        return assentoApplication.getAssentoById(id);
    }
    
    public List<AssentoDTO> getAllAssento() {
        return assentoApplication.getAllAssento();
    }
    
    public void updateAssento( AssentoDTO assento) {
        assentoApplication.updateAssento( assento);
    }
    
    public void deleteAssento(long id) {
        assentoApplication.deleteAssento(id);
    }
}
