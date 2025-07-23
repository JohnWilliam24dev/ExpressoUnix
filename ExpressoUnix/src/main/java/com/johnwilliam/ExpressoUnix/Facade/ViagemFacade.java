package com.johnwilliam.ExpressoUnix.Facade;

import java.util.List;
import org.springframework.stereotype.Component;
import com.johnwilliam.ExpressoUnix.Applications.ViagemApplication;
import com.johnwilliam.ExpressoUnix.DTO.ViagemDTO;

@Component
public class ViagemFacade {
    private final ViagemApplication viagemApplication;
    
    public ViagemFacade(ViagemApplication viagemApplication) {
        this.viagemApplication = viagemApplication;
    }
    
    public void createViagem(ViagemDTO viagem) {
        viagemApplication.createViagem(viagem);
    }
    
    public ViagemDTO getViagemById(long id) {
        return viagemApplication.getViagemById(id);
    }
    
    public List<ViagemDTO> getAllViagem() {
        return viagemApplication.getAllViagem();
    }
    
    public void updateViagem(ViagemDTO viagem) {
        viagemApplication.updateViagem(viagem);
    }
    
    public void deleteViagem(long id) {
        viagemApplication.deleteViagem(id);
    }
}
