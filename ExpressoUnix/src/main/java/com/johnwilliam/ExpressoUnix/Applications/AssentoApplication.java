package com.johnwilliam.ExpressoUnix.Applications;

import java.util.List;

import org.springframework.stereotype.Service;

import com.johnwilliam.ExpressoUnix.DTO.AssentoDTO;
import com.johnwilliam.ExpressoUnix.Entities.Assento;
import com.johnwilliam.ExpressoUnix.Enums.StatusAssento;
import com.johnwilliam.ExpressoUnix.Mappers.AssentoMapper;
import com.johnwilliam.ExpressoUnix.Models.AssentoModels;
import com.johnwilliam.ExpressoUnix.Repositories.AssentoRepository;
import com.johnwilliam.ExpressoUnix.Repositories.ViagemRepository;

@Service
public class AssentoApplication {
    private AssentoRepository assentoRepository;
    private ViagemRepository viagemRepository;
    private AssentoMapper assentoMapper;

    public AssentoApplication(AssentoRepository assentoRepository, ViagemRepository viagemRepository, AssentoMapper assentoMapper){
        this.assentoRepository=assentoRepository;
        this.viagemRepository=viagemRepository;
        this.assentoMapper=assentoMapper;
    }

    public void createAssento(AssentoDTO assento){
        viagemRepository.getViagemById(assento.getIdViagem()); // 404 se a viagem nao existe
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
        viagemRepository.getViagemById(assento.getIdViagem()); // 404 se a viagem nao existe
        Assento entity=assentoMapper.DTOtoEntity(assento);
        assentoRepository.updateAssento( assentoMapper.entityToModel(entity));
    }

    /** Altera apenas o status do assento (usado na venda/cancelamento de passagens). */
    public void alterarStatus(long id, StatusAssento status) {
        AssentoModels model = assentoRepository.getAssentoById(id);
        model.setStatusAssento(status);
        assentoRepository.updateAssento(model);
    }

    public void deleteAssento(long id) {
        assentoRepository.deleteAssento(id);
    }
}
