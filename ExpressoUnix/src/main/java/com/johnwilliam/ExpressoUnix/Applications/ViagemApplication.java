package com.johnwilliam.ExpressoUnix.Applications;

import java.util.List;
import org.springframework.stereotype.Service;


import com.johnwilliam.ExpressoUnix.Entities.Viagem;
import com.johnwilliam.ExpressoUnix.Mappers.ViagemMapper;

import com.johnwilliam.ExpressoUnix.DTO.ViagemDTO;

import com.johnwilliam.ExpressoUnix.Repositories.ViagemRepository;

@Service
public class ViagemApplication {
    private ViagemRepository viagemRepository;
    private AssentoApplication assentoApplication;
    private ViagemMapper viagemMapper;
    
    public ViagemApplication(ViagemRepository viagemRepository, ViagemMapper viagemMapper,AssentoApplication assentoApplication){
        this.viagemRepository = viagemRepository;
        this.viagemMapper=viagemMapper;
        this.assentoApplication=assentoApplication;
        
    }
    
    public void createViagem(ViagemDTO viagemDTO){
        Viagem entity= viagemMapper.DTOtoEntity(viagemDTO);
        viagemRepository.createViagem(viagemMapper.entityToModel(entity));

        
        assentoApplication.createAllAssento(entity.disponibilizarAssentos());
        

        
    }
    
    public ViagemDTO getViagemById(long id) {

        return viagemMapper.modelToDTO(viagemRepository.getViagemById(id)); 
    }
    
    public List<ViagemDTO> getAllViagem() {
        return viagemMapper.modelToDTOList(viagemRepository.getAllViagem());
    }
    
    public void updateViagem(ViagemDTO viagem) {
        Viagem entity= viagemMapper.DTOtoEntity(viagem);
        viagemRepository.updateViagem( viagemMapper.entityToModel(entity));
    }
    
    public void deleteViagem(long id) {
        viagemRepository.deleteViagem(id);
    }

    
}
