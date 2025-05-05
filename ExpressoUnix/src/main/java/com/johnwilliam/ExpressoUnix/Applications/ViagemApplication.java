package com.johnwilliam.ExpressoUnix.Applications;

import java.util.List;
import org.springframework.stereotype.Service;

import com.johnwilliam.ExpressoUnix.Models.ViagemModels;
import com.johnwilliam.ExpressoUnix.Repositories.ViagemRepository;

@Service
public class ViagemApplication {
    private ViagemRepository viagemRepository;
    
    public ViagemApplication(ViagemRepository viagemRepository){
        this.viagemRepository = viagemRepository;
    }
    
    public void createViagem(ViagemModels viagem){
        viagemRepository.createViagem(viagem);
    }
    
    public ViagemModels getViagemById(long id) {
        return viagemRepository.getViagemById(id);
    }
    
    public List<ViagemModels> getAllViagem() {
        return viagemRepository.getAllViagem();
    }
    
    public void updateViagem(ViagemModels viagem) {
        viagemRepository.updateViagem( viagem);
    }
    
    public void deleteViagem(long id) {
        viagemRepository.deleteViagem(id);
    }
}
