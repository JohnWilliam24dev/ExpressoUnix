package com.johnwilliam.ExpressoUnix.Facade;

import java.util.List;
import org.springframework.stereotype.Component;
import com.johnwilliam.ExpressoUnix.Applications.ViagemApplication;
import com.johnwilliam.ExpressoUnix.Entities.Viagem;

@Component
public class ViagemFacade {
    private final ViagemApplication viagemApplication;
    
    public ViagemFacade(ViagemApplication viagemApplication) {
        this.viagemApplication = viagemApplication;
    }
    
    public void createViagem(Viagem viagem) {
        viagemApplication.createViagem(viagem);
    }
    
    public Viagem getViagemById(long id) {
        return viagemApplication.getViagemById(id);
    }
    
    public List<Viagem> getAllViagem() {
        return viagemApplication.getAllViagem();
    }
    
    public void updateViagem(Viagem viagem) {
        viagemApplication.updateViagem(viagem);
    }
    
    public void deleteViagem(long id) {
        viagemApplication.deleteViagem(id);
    }
}
