package com.johnwilliam.ExpressoUnix.Applications;

import java.util.List;
import org.springframework.stereotype.Service;
import com.johnwilliam.ExpressoUnix.DTO.AssentoDTO;
import com.johnwilliam.ExpressoUnix.Entities.Assento;
import com.johnwilliam.ExpressoUnix.Mappers.AssentoMapper;
import com.johnwilliam.ExpressoUnix.Models.AssentoModels;
import com.johnwilliam.ExpressoUnix.Repositories.AssentoRepository;
@Service
public class AssentoApplication {
    private AssentoRepository assentoRepository;
    private AssentoMapper assentoMapper;
    public AssentoApplication(AssentoRepository assentoRepository,AssentoMapper assentoMapper){
        this.assentoRepository=assentoRepository;
        this.assentoMapper=assentoMapper;
    }
    public void createAssento(AssentoDTO assento){
        Assento entity=assentoMapper.DTOtoEntity(assento);
        AssentoModels model=assentoMapper.entityToModel(entity);
        assentoRepository.createAssento(model);
    }
    public void createAllAssento(List<AssentoModels> lista_AssentoModel){
        
        assentoRepository.createAllAssento(lista_AssentoModel);
    }
    public AssentoDTO getAssentoById(long id) {

        return assentoMapper.modelToDTO(assentoRepository.getAssentoById(id));
    }

    public List<AssentoDTO> getAllAssento() {
        return assentoMapper.modelToDTOList(assentoRepository.getAllAssento());
    }

    public void updateAssento( AssentoDTO assento) {
        Assento entity=assentoMapper.DTOtoEntity(assento);
        assentoRepository.updateAssento( assentoMapper.entityToModel(entity));
    }

    public void deleteAssento(long id) {
        assentoRepository.deleteAssento(id);
    }
}
