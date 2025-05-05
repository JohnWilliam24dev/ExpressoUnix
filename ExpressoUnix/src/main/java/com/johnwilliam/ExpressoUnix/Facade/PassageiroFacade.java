package com.johnwilliam.ExpressoUnix.Facade;

import java.util.List;
import org.springframework.stereotype.Component;
import com.johnwilliam.ExpressoUnix.Applications.PassageiroApplication;

import com.johnwilliam.ExpressoUnix.Models.PassageiroModels;

@Component
public class PassageiroFacade {
    private final PassageiroApplication passageiroApplication;
    
    public PassageiroFacade(PassageiroApplication passageiroApplication) {
        this.passageiroApplication = passageiroApplication;
    }
    
    public void createPassageiro(PassageiroModels passageiro) {
        passageiroApplication.createPassageiro(passageiro);
    }
    
    public PassageiroModels getPassageiroById(long id) {
        return passageiroApplication.getPassageiroById(id);
    }
    
    public List<PassageiroModels> getAllPassageiro() {
        return passageiroApplication.getAllPassageiro();
    }
    
    public void updatePassageiro( PassageiroModels passageiro) {
        passageiroApplication.updatePassageiro(passageiro);
    }
    
    public void deletePassageiro(long id) {
        passageiroApplication.deletePassageiro(id);
    }
}
