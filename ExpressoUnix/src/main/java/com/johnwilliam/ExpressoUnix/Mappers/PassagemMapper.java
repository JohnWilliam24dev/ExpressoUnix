package com.johnwilliam.ExpressoUnix.Mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.johnwilliam.ExpressoUnix.DTO.PassagemDTO;
import com.johnwilliam.ExpressoUnix.Entities.Passagem;
import com.johnwilliam.ExpressoUnix.Models.PassagemModels;
import com.johnwilliam.ExpressoUnix.Facade.AssentoFacade;
import com.johnwilliam.ExpressoUnix.Facade.PassageiroFacade;
import com.johnwilliam.ExpressoUnix.Facade.ViagemFacade;

@Component
public class PassagemMapper {
    private final ViagemFacade viagemFacade;
    private final AssentoFacade assentoFacade;
    private final PassageiroFacade passageiroFacade;

    public PassagemMapper(ViagemFacade viagemFacade,
                          AssentoFacade assentoFacade,
                          PassageiroFacade passageiroFacade) {
        this.viagemFacade = viagemFacade;
        this.assentoFacade = assentoFacade;
        this.passageiroFacade = passageiroFacade;
    }

    public Passagem DTOtoEntity(PassagemDTO dto) {
        return new Passagem(
            dto.getId(),
            dto.getStatus(),
            viagemFacade.getViagemById(dto.getIdViagem()),
            assentoFacade.getAssentoById(dto.getIdAssento()),
            passageiroFacade.getPassageiroById(dto.getIdPassageiro()),
            dto.getDataPassagem(),
            dto.getHoraPassagem(),
            dto.getOrigem(),
            dto.getDestino(),
            dto.getDistancia(),
            dto.getPreco()
        );
    }

    

    public Passagem modelToEntity(PassagemModels model) {
        return new Passagem(
            model.getId(),
            model.getStatus(),
            viagemFacade.getViagemById(model.getIdViagem()),
            assentoFacade.getAssentoById(model.getIdAssento()),
            passageiroFacade.getPassageiroById(model.getIdPassageiro()),
            model.getDataPassagem(),
            model.getHoraPassagem(),
            model.getOrigem(),
            model.getDestino(),
            model.getDistancia(),
            model.getPreco()
        );
    }

    public PassagemDTO entityToDTO(Passagem entity) {
        return new PassagemDTO(
            entity.getStatus(),
            entity.getViagem().getId(),
            entity.getAssento().getId(),
            entity.getPassageiro().getId(),
            entity.getDataPassagem(),
            entity.getHoraPassagem(),
            entity.getOrigem().getValue(),
            entity.getDestino().getValue(),
            entity.getDistancia(),
            entity.getPreco()
        );
    }

    public PassagemModels entityToModel(Passagem entity) {
        PassagemModels model = new PassagemModels();
        model.setId(entity.getId());
        model.setStatus(entity.getStatus());
        model.setIdViagem(entity.getViagem().getId());
        model.setIdAssento(entity.getAssento().getId());
        model.setIdPassageiro(entity.getPassageiro().getId());
        model.setDataPassagem(entity.getDataPassagem());
        model.setHoraPassagem(entity.getHoraPassagem());
        model.setOrigem(entity.getOrigem().getValue());
        model.setDestino(entity.getDestino().getValue());
        model.setDistancia(entity.getDistancia());
        model.setPreco(entity.getPreco());
        return model;
    }
    public PassagemDTO modelToDTO(PassagemModels entity) {
        return new PassagemDTO(
            entity.getStatus(),
            entity.getViagem().getId(),
            entity.getAssento().getId(),
            entity.getPassageiro().getId(),
            entity.getDataPassagem(),
            entity.getHoraPassagem(),
            entity.getOrigem(),
            entity.getDestino(),
            entity.getDistancia(),
            entity.getPreco()
        );
    }

    // List converters
    public List<PassagemDTO> modelToDTOList(List<PassagemModels> list) {
        return list.stream().map(this::modelToDTO).collect(Collectors.toList());
    }
    public List<PassagemDTO> entityToDTOList(List<Passagem> list) {
        return list.stream()
                   .map(this::entityToDTO)
                   .collect(Collectors.toList());
    }

    public List<Passagem> DTOtoEntityList(List<PassagemDTO> list) {
        return list.stream()
                   .map(this::DTOtoEntity)
                   .collect(Collectors.toList());
    }

    public List<PassagemModels> entityToModelList(List<Passagem> list) {
        return list.stream()
                   .map(this::entityToModel)
                   .collect(Collectors.toList());
    }

    public List<Passagem> modelToEntityList(List<PassagemModels> list) {
        return list.stream()
                   .map(this::modelToEntity)
                   .collect(Collectors.toList());
    }
}
