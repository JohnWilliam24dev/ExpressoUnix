package com.johnwilliam.ExpressoUnix.Facade;

import java.util.List;
import org.springframework.stereotype.Component;
import com.johnwilliam.ExpressoUnix.Applications.PassageiroApplication;

import com.johnwilliam.ExpressoUnix.DTO.PassageiroDTO;

@Component
public class PassageiroFacade {
    private final PassageiroApplication passageiroApplication;
    
    public PassageiroFacade(PassageiroApplication passageiroApplication) {
        this.passageiroApplication = passageiroApplication;
    }
    
    public void createPassageiro(PassageiroDTO passageiro) {
        passageiroApplication.createPassageiro(passageiro);
    }
    
    public PassageiroDTO getPassageiroById(long id) {
        return passageiroApplication.getPassageiroById(id);
    }
    
    public List<PassageiroDTO> getAllPassageiro() {
        return passageiroApplication.getAllPassageiro();
    }
    
    public void updatePassageiro( PassageiroDTO passageiro) {
        passageiroApplication.updatePassageiro(passageiro);
    }
    
    public void deletePassageiro(long id) {
        passageiroApplication.deletePassageiro(id);
    }
}
