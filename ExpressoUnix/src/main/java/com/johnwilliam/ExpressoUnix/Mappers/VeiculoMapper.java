// VeiculoMapper.java
package com.johnwilliam.ExpressoUnix.Mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.johnwilliam.ExpressoUnix.DTO.VeiculoDTO;
import com.johnwilliam.ExpressoUnix.Entities.Veiculo;
import com.johnwilliam.ExpressoUnix.Models.VeiculoModels;

@Component
public class VeiculoMapper {

    public Veiculo DTOtoEntity(VeiculoDTO dto) {
        return new Veiculo(
            dto.getId(),
            dto.getClasse(),
            dto.getCapacidade(),
            dto.getStatusVeiculo()
        );
    }

    public VeiculoModels entityToModel(Veiculo entity) {
        VeiculoModels model = new VeiculoModels();
        model.setId(entity.getId());
        model.setClasse(entity.getClasse());
        model.setCapacidade(entity.getCapacidade());
        model.setStatusVeiculo(entity.getStatusVeiculo());
        return model;
    }

    public VeiculoDTO entityToDTO(Veiculo entity) {
        VeiculoDTO dto = new VeiculoDTO();
        dto.setId(entity.getId());
        dto.setClasse(entity.getClasse());
        dto.setCapacidade(entity.getCapacidade());
        dto.setStatusVeiculo(entity.getStatusVeiculo());
        return dto;
    }

    public Veiculo modelToEntity(VeiculoModels model) {
        Veiculo entity = new Veiculo(
            model.getId(),
            model.getClasse(),
            model.getCapacidade(),
            model.getStatusVeiculo()
        );
        return entity;
    }

    public VeiculoDTO modelToDTO(VeiculoModels entity) {
        VeiculoDTO dto = new VeiculoDTO();
        dto.setId(entity.getId());
        dto.setClasse(entity.getClasse());
        dto.setCapacidade(entity.getCapacidade());
        dto.setStatusVeiculo(entity.getStatusVeiculo());
        return dto;
    }

    // List converters
    public List<VeiculoDTO> modelToDTOList(List<VeiculoModels> list) {
        return list.stream().map(this::modelToDTO).collect(Collectors.toList());
    }
    public List<VeiculoDTO> entityToDTOList(List<Veiculo> list) {
        return list.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public List<Veiculo> DTOtoEntityList(List<VeiculoDTO> list) {
        return list.stream().map(this::DTOtoEntity).collect(Collectors.toList());
    }

    public List<VeiculoModels> entityToModelList(List<Veiculo> list) {
        return list.stream().map(this::entityToModel).collect(Collectors.toList());
    }

    public List<Veiculo> modelToEntityList(List<VeiculoModels> list) {
        return list.stream().map(this::modelToEntity).collect(Collectors.toList());
    }
}
