package com.johnwilliam.ExpressoUnix.Facade;

import java.util.List;
import org.springframework.stereotype.Component;
import com.johnwilliam.ExpressoUnix.Applications.PassagemApplication;

import com.johnwilliam.ExpressoUnix.Models.PassagemModels;

@Component
public class PassagemFacade {
    private final PassagemApplication passagemApplication;
    
    public PassagemFacade(PassagemApplication passagemApplication) {
        this.passagemApplication = passagemApplication;
    }
    
    public void createPassagem(PassagemModels passagem) {
        passagemApplication.createPassagem(passagem);
    }
    
    public PassagemModels getPassagemById(long id) {
        return passagemApplication.getPassagemById(id);
    }
    
    public List<PassagemModels> getAllPassagem() {
        return passagemApplication.getAllPassagem();
    }
    
    public void updatePassagem(PassagemModels passagem) {
        passagemApplication.updatePassagem( passagem);
    }
    
    public void deletePassagem(long id) {
        passagemApplication.deletePassagem(id);
    }
}
