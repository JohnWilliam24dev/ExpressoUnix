package com.johnwilliam.ExpressoUnix.Facade;

import java.util.List;
import org.springframework.stereotype.Component;
import com.johnwilliam.ExpressoUnix.Applications.PassageiroApplication;
import com.johnwilliam.ExpressoUnix.Entities.Passageiro;

@Component
public class PassageiroFacade {
    private final PassageiroApplication passageiroApplication;
    
    public PassageiroFacade(PassageiroApplication passageiroApplication) {
        this.passageiroApplication = passageiroApplication;
    }
    
    public void createPassageiro(Passageiro passageiro) {
        passageiroApplication.createPassageiro(passageiro);
    }
    
    public Passageiro getPassageiroById(long id) {
        return passageiroApplication.getPassageiroById(id);
    }
    
    public List<Passageiro> getAllPassageiro() {
        return passageiroApplication.getAllPassageiro();
    }
    
    public void updatePassageiro( Passageiro passageiro) {
        passageiroApplication.updatePassageiro(passageiro);
    }
    
    public void deletePassageiro(long id) {
        passageiroApplication.deletePassageiro(id);
    }
}
