package com.johnwilliam.ExpressoUnix.Facade;

import java.util.List;
import org.springframework.stereotype.Component;
import com.johnwilliam.ExpressoUnix.Applications.PassagemApplication;
import com.johnwilliam.ExpressoUnix.Entities.Passagem;

@Component
public class PassagemFacade {
    private final PassagemApplication passagemApplication;
    
    public PassagemFacade(PassagemApplication passagemApplication) {
        this.passagemApplication = passagemApplication;
    }
    
    public void createPassagem(Passagem passagem) {
        passagemApplication.createPassagem(passagem);
    }
    
    public Passagem getPassagemById(long id) {
        return passagemApplication.getPassagemById(id);
    }
    
    public List<Passagem> getAllPassagem() {
        return passagemApplication.getAllPassagem();
    }
    
    public void updatePassagem(Passagem passagem) {
        passagemApplication.updatePassagem( passagem);
    }
    
    public void deletePassagem(long id) {
        passagemApplication.deletePassagem(id);
    }
}
