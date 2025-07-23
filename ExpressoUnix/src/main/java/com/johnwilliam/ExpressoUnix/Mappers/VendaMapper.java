// VendaMapper.java
package com.johnwilliam.ExpressoUnix.Mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.johnwilliam.ExpressoUnix.DTO.VendaDTO;
import com.johnwilliam.ExpressoUnix.Entities.Venda;
import com.johnwilliam.ExpressoUnix.Models.VendaModels;

@Component
public class VendaMapper {

    public Venda DTOtoEntity(VendaDTO dto) {
        return new Venda(
            dto.getId(),
            dto.getIdFuncionario(),
            dto.getIdPassagem(),
            dto.getHorarioEmissao()
        );
    }

    public VendaModels entityToModel(Venda entity) {
        VendaModels model = new VendaModels();
        model.setId(entity.getId());
        model.setIdFuncionario(entity.getFuncionario());
        model.setIdPassagem(entity.getPassagem());
        model.setHorarioEmissao(entity.getHorarioEmissao());
        return model;
    }

    public VendaDTO entityToDTO(Venda entity) {
        VendaDTO dto = new VendaDTO();
        dto.setId(entity.getId());
        dto.setIdFuncionario(entity.getFuncionario());
        dto.setIdPassagem(entity.getPassagem());
        dto.setHorarioEmissao(entity.getHorarioEmissao());
        return dto;
    }

    public Venda modelToEntity(VendaModels model) {
        Venda entity = new Venda(
            model.getId(),
            model.getIdFuncionario(),
            model.getIdPassagem(),
            model.getHorarioEmissao()
        );
        return entity;
    }
     public VendaDTO modelToDTO(VendaModels entity) {
        VendaDTO dto = new VendaDTO();
        dto.setId(entity.getId());
        dto.setIdFuncionario(entity.getFuncionario().getId());
        dto.setIdPassagem(entity.getPassagem().getId());
        dto.setHorarioEmissao(entity.getHorarioEmissao());
        return dto;
    }
    // List converters
    public List<VendaDTO> modelToDTOList(List<VendaModels> list) {
        return list.stream().map(this::modelToDTO).collect(Collectors.toList());
    }
    public List<VendaDTO> entityToDTOList(List<Venda> list) {
        return list.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public List<Venda> DTOtoEntityList(List<VendaDTO> list) {
        return list.stream().map(this::DTOtoEntity).collect(Collectors.toList());
    }

    public List<VendaModels> entityToModelList(List<Venda> list) {
        return list.stream().map(this::entityToModel).collect(Collectors.toList());
    }

    public List<Venda> modelToEntityList(List<VendaModels> list) {
        return list.stream().map(this::modelToEntity).collect(Collectors.toList());
    }
}
