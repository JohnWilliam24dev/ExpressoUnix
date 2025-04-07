package com.johnwilliam.ExpressoUnix.Applications;

import java.util.List;
import org.springframework.stereotype.Service;
import com.johnwilliam.ExpressoUnix.Entities.Viagem;
import com.johnwilliam.ExpressoUnix.Repositories.ViagemRepository;

@Service
public class ViagemApplication {
    private ViagemRepository viagemRepository;
    
    public ViagemApplication(ViagemRepository viagemRepository){
        this.viagemRepository = viagemRepository;
    }
    
    public void createViagem(Viagem viagem){
        viagemRepository.createViagem(viagem);
    }
    
    public Viagem getViagemById(long id) {
        return viagemRepository.getViagemById(id);
    }
    
    public List<Viagem> getAllViagem() {
        return viagemRepository.getAllViagem();
    }
    
    public void updateViagem(Viagem viagem) {
        viagemRepository.updateViagem( viagem);
    }
    
    public void deleteViagem(long id) {
        viagemRepository.deleteViagem(id);
    }
}
