package com.johnwilliam.ExpressoUnix.Facade;

import java.util.List;
import org.springframework.stereotype.Component;
import com.johnwilliam.ExpressoUnix.Applications.ViagemApplication;
import com.johnwilliam.ExpressoUnix.Models.ViagemModels;

@Component
public class ViagemFacade {
    private final ViagemApplication viagemApplication;
    
    public ViagemFacade(ViagemApplication viagemApplication) {
        this.viagemApplication = viagemApplication;
    }
    
    public void createViagem(ViagemModels viagem) {
        viagemApplication.createViagem(viagem);
    }
    
    public ViagemModels getViagemById(long id) {
        return viagemApplication.getViagemById(id);
    }
    
    public List<ViagemModels> getAllViagem() {
        return viagemApplication.getAllViagem();
    }
    
    public void updateViagem(ViagemModels viagem) {
        viagemApplication.updateViagem(viagem);
    }
    
    public void deleteViagem(long id) {
        viagemApplication.deleteViagem(id);
    }
}
