package com.johnwilliam.ExpressoUnix.Facade;

import java.util.List;
import org.springframework.stereotype.Component;
import com.johnwilliam.ExpressoUnix.Applications.PassagemApplication;

import com.johnwilliam.ExpressoUnix.DTO.PassagemDTO;

@Component
public class PassagemFacade {
    private final PassagemApplication passagemApplication;
    
    public PassagemFacade(PassagemApplication passagemApplication) {
        this.passagemApplication = passagemApplication;
    }
    
    public void createPassagem(PassagemDTO passagem) {
        passagemApplication.createPassagem(passagem);
    }
    
    public PassagemDTO getPassagemById(long id) {
        return passagemApplication.getPassagemById(id);
    }
    
    public List<PassagemDTO> getAllPassagem() {
        return passagemApplication.getAllPassagem();
    }
    
    public void updatePassagem(PassagemDTO passagem) {
        passagemApplication.updatePassagem( passagem);
    }
    
    public void deletePassagem(long id) {
        passagemApplication.deletePassagem(id);
    }
}
