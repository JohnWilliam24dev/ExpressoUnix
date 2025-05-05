package com.johnwilliam.ExpressoUnix.Applications;

import java.util.List;
import org.springframework.stereotype.Service;

import com.johnwilliam.ExpressoUnix.Models.PassageiroModels;
import com.johnwilliam.ExpressoUnix.Repositories.PassageiroRepository;

@Service
public class PassageiroApplication {
    private PassageiroRepository passageiroRepository;
    
    public PassageiroApplication(PassageiroRepository passageiroRepository){
        this.passageiroRepository = passageiroRepository;
    }
    
    public void createPassageiro(PassageiroModels passageiro){
        passageiroRepository.createPassageiro(passageiro);
    }
    
    public PassageiroModels getPassageiroById(long id) {
        return passageiroRepository.getPassageiroById(id);
    }
    
    public List<PassageiroModels> getAllPassageiro() {
        return passageiroRepository.getAllPassageiro();
    }
    
    public void updatePassageiro( PassageiroModels passageiro) {
        passageiroRepository.updatePassageiro( passageiro);
    }
    
    public void deletePassageiro(long id) {
        passageiroRepository.deletePassageiro(id);
    }
}
