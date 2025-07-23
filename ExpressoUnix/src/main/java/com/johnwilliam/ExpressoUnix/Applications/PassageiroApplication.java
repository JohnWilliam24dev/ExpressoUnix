package com.johnwilliam.ExpressoUnix.Applications;

import java.util.List;
import org.springframework.stereotype.Service;

import com.johnwilliam.ExpressoUnix.DTO.PassageiroDTO;
import com.johnwilliam.ExpressoUnix.Entities.Passageiro;
import com.johnwilliam.ExpressoUnix.Mappers.PassageiroMapper;
import com.johnwilliam.ExpressoUnix.Repositories.PassageiroRepository;

@Service
public class PassageiroApplication {
    private PassageiroRepository passageiroRepository;
    private PassageiroMapper passageiroMapper;
    
    public PassageiroApplication(PassageiroRepository passageiroRepository, PassageiroMapper passageiroMapper){
        this.passageiroRepository = passageiroRepository;
        this.passageiroMapper= passageiroMapper;
    }
    
    public void createPassageiro(PassageiroDTO passageiro){
        Passageiro entity=passageiroMapper.DTOtoEntity(passageiro);
        passageiroRepository.createPassageiro(passageiroMapper.entityToModel(entity));
    }
    
    public PassageiroDTO getPassageiroById(long id) {
        return passageiroMapper.modelToDTO(passageiroRepository.getPassageiroById(id)); 
    }
    
    public List<PassageiroDTO> getAllPassageiro() {
        return passageiroMapper.modelToDTOList(passageiroRepository.getAllPassageiro()); 
    }
    
    public void updatePassageiro( PassageiroDTO passageiro) {
        Passageiro entity=passageiroMapper.DTOtoEntity(passageiro);
        passageiroRepository.updatePassageiro(passageiroMapper.entityToModel(entity));
    }
    
    public void deletePassageiro(long id) {
        passageiroRepository.deletePassageiro(id);
    }
}
