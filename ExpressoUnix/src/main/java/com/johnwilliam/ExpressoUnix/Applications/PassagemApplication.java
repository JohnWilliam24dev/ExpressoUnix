package com.johnwilliam.ExpressoUnix.Applications;

import java.util.List;
import org.springframework.stereotype.Service;
import com.johnwilliam.ExpressoUnix.Entities.Passagem;
import com.johnwilliam.ExpressoUnix.Repositories.PassagemRepository;

@Service
public class PassagemApplication {
    private PassagemRepository passagemRepository;
    
    public PassagemApplication(PassagemRepository passagemRepository){
        this.passagemRepository = passagemRepository;
    }
    
    public void createPassagem(Passagem passagem){
        passagemRepository.createPassagem(passagem);
    }
    
    public Passagem getPassagemById(long id) {
        return passagemRepository.getPassagemById(id);
    }
    
    public List<Passagem> getAllPassagem() {
        return passagemRepository.getAllPassagem();
    }
    
    public void updatePassagem( Passagem passagem) {
        passagemRepository.updatePassagem( passagem);
    }
    
    public void deletePassagem(long id) {
        passagemRepository.deletePassagem(id);
    }
}
