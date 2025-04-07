package com.johnwilliam.ExpressoUnix.Applications;

import java.util.List;
import org.springframework.stereotype.Service;
import com.johnwilliam.ExpressoUnix.Entities.Passageiro;
import com.johnwilliam.ExpressoUnix.Repositories.PassageiroRepository;

@Service
public class PassageiroApplication {
    private PassageiroRepository passageiroRepository;
    
    public PassageiroApplication(PassageiroRepository passageiroRepository){
        this.passageiroRepository = passageiroRepository;
    }
    
    public void createPassageiro(Passageiro passageiro){
        passageiroRepository.createPassageiro(passageiro);
    }
    
    public Passageiro getPassageiroById(long id) {
        return passageiroRepository.getPassageiroById(id);
    }
    
    public List<Passageiro> getAllPassageiro() {
        return passageiroRepository.getAllPassageiro();
    }
    
    public void updatePassageiro( Passageiro passageiro) {
        passageiroRepository.updatePassageiro( passageiro);
    }
    
    public void deletePassageiro(long id) {
        passageiroRepository.deletePassageiro(id);
    }
}
