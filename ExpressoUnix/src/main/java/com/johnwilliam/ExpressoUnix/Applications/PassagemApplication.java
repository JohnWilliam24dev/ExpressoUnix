package com.johnwilliam.ExpressoUnix.Applications;

import java.util.List;
import org.springframework.stereotype.Service;

import com.johnwilliam.ExpressoUnix.DTO.PassagemDTO;
import com.johnwilliam.ExpressoUnix.Entities.Passagem;
import com.johnwilliam.ExpressoUnix.Mappers.PassagemMapper;
import com.johnwilliam.ExpressoUnix.Repositories.PassagemRepository;

@Service
public class PassagemApplication {
    private PassagemRepository passagemRepository;
    private PassagemMapper passagemMapper;
    
    public PassagemApplication(PassagemRepository passagemRepository,PassagemMapper passagemMapper){
        this.passagemRepository = passagemRepository;
        this.passagemMapper= passagemMapper;
    }
    
    public void createPassagem(PassagemDTO passagem){
        Passagem entity = passagemMapper.DTOtoEntity(passagem);
        passagemRepository.createPassagem(passagemMapper.entityToModel(entity));
    }
    
    public PassagemDTO getPassagemById(long id) {

        return passagemMapper.modelToDTO(passagemRepository.getPassagemById(id)); 
    }
    
    public List<PassagemDTO> getAllPassagem() {
        return passagemMapper.modelToDTOList(passagemRepository.getAllPassagem()); 
    }
    
    public void updatePassagem( PassagemDTO passagem) {
        Passagem entity = passagemMapper.DTOtoEntity(passagem);
        passagemRepository.updatePassagem( passagemMapper.entityToModel(entity));
    }
    
    public void deletePassagem(long id) {
        passagemRepository.deletePassagem(id);
    }
}
