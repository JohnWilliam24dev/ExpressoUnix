package com.johnwilliam.ExpressoUnix.Mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.johnwilliam.ExpressoUnix.DTO.RotaDTO;
import com.johnwilliam.ExpressoUnix.Models.RotaModels;

/** Rota nao tem regra de dominio propria, entao o mapeamento e direto DTO <-> Model. */
@Component
public class RotaMapper {

    public RotaModels DTOtoModel(RotaDTO dto) {
        RotaModels model = new RotaModels();
        model.setId(dto.getId());
        model.setOrigem(dto.getOrigem().trim());
        model.setDestino(dto.getDestino().trim());
        model.setDistanciaKm(dto.getDistanciaKm());
        model.setPrecoBase(dto.getPrecoBase());
        return model;
    }

    public RotaDTO modelToDTO(RotaModels model) {
        RotaDTO dto = new RotaDTO();
        dto.setId(model.getId());
        dto.setOrigem(model.getOrigem());
        dto.setDestino(model.getDestino());
        dto.setDistanciaKm(model.getDistanciaKm());
        dto.setPrecoBase(model.getPrecoBase());
        return dto;
    }

    public List<RotaDTO> modelToDTOList(List<RotaModels> list) {
        return list.stream().map(this::modelToDTO).collect(Collectors.toList());
    }
}
