package com.johnwilliam.ExpressoUnix.Mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.johnwilliam.ExpressoUnix.DTO.ViagemDTO;

import com.johnwilliam.ExpressoUnix.Entities.Viagem;
import com.johnwilliam.ExpressoUnix.Facade.VeiculoFacade;
import com.johnwilliam.ExpressoUnix.Models.ViagemModels;

@Component
public class ViagemMapper {
    private VeiculoFacade veiculoFacade;
    public ViagemMapper(VeiculoFacade veiculoFacade){
        this.veiculoFacade=veiculoFacade;

    }
    public Viagem DTOtoEntity(ViagemDTO dto) {
        return new Viagem(
            dto.getId(),
            veiculoFacade.getVeiculoById(dto.getIdVeiculo()),
            dto.getDataViagem(),
            dto.getHoraViagem(),
            dto.getOrigem(),
            dto.getDestino()
        );
    }

    public ViagemModels entityToModel(Viagem entity) {
        ViagemModels model = new ViagemModels();
        model.setId(entity.getId());
        model.setIdVeiculo(entity.getVeiculo().getId());
        model.setDataViagem(entity.getDataViagem());
        model.setHoraViagem(entity.getHoraViagem());
        model.setOrigem(entity.getOrigem().getValue());
        model.setDestino(entity.getDestino().getValue());
        return model;
    }

    public ViagemDTO entityToDTO(Viagem entity) {
        ViagemDTO dto = new ViagemDTO();
        dto.setId(entity.getId());
        dto.setIdVeiculo(entity.getVeiculo().getId());
        dto.setDataViagem(entity.getDataViagem());
        dto.setHoraViagem(entity.getHoraViagem());
        dto.setOrigem(entity.getOrigem().getValue());
        dto.setDestino(entity.getDestino().getValue());
        return dto;
    }

    public Viagem modelToEntity(ViagemModels model) {
        Viagem entity = new Viagem(
            model.getId(),
            veiculoFacade.getVeiculoById(model.getIdVeiculo()), // preencha conforme seu construtor
            model.getDataViagem(),
            model.getHoraViagem(),
            model.getOrigem(),
            model.getDestino()
        );
        return entity;
    }
    public ViagemDTO modelToDTO(ViagemModels entity) {
        ViagemDTO dto = new ViagemDTO();
        dto.setId(entity.getId());
        dto.setIdVeiculo(entity.getVeiculo().getId());
        dto.setDataViagem(entity.getDataViagem());
        dto.setHoraViagem(entity.getHoraViagem());
        dto.setOrigem(entity.getOrigem());
        dto.setDestino(entity.getDestino());
        return dto;
    }
    // List converters
    public List<ViagemDTO> modelToDTOList(List<ViagemModels> list) {
        return list.stream().map(this::modelToDTO).collect(Collectors.toList());
    }
    public List<ViagemDTO> entityToDTOList(List<Viagem> list) {
        return list.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public List<Viagem> DTOtoEntityList(List<ViagemDTO> list) {
        return list.stream()
                   .map(this::DTOtoEntity)
                   .collect(Collectors.toList());
    }

    public List<ViagemModels> entityToModelList(List<Viagem> list) {
        return list.stream().map(this::entityToModel).collect(Collectors.toList());
    }

    public List<Viagem> modelToEntityList(List<ViagemModels> list) {
        return list.stream().map(this::modelToEntity).collect(Collectors.toList());
    }
}
