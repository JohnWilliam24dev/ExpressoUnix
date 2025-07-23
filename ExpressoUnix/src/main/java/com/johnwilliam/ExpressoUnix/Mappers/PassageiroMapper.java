package com.johnwilliam.ExpressoUnix.Mappers;

import com.johnwilliam.ExpressoUnix.DTO.PassageiroDTO;
import com.johnwilliam.ExpressoUnix.Entities.Passageiro;
import com.johnwilliam.ExpressoUnix.Models.PassageiroModels;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PassageiroMapper {

    public Passageiro DTOtoEntity(PassageiroDTO dto) {
        return new Passageiro(
            dto.getId(),
            dto.getNome(),
            dto.getEmail(),
            dto.getTelefone(),
            dto.getCpf(),
            dto.getDataNascimento()
        );
    }

    public Passageiro modelToEntity(PassageiroModels model) {
        return new Passageiro(
            model.getId(),
            model.getNome(),
            model.getEmail(),
            model.getTelefone(),
            model.getCpf(),
            model.getDataNascimento()
        );
    }

    public PassageiroModels entityToModel(Passageiro entity) {
        PassageiroModels model = new PassageiroModels();
        model.setId(entity.getId());
        model.setNome(entity.getNome().getValue());
        model.setEmail(entity.getEmail().getValue());
        model.setTelefone(entity.getTelefone().getValue());
        model.setCpf(entity.getCpf().getValue());
        model.setDataNascimento(entity.getDataNascimento().getValue());
        return model;
    }

    public PassageiroDTO entityToDTO(Passageiro entity) {
        PassageiroDTO dto = new PassageiroDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome().getValue());
        dto.setEmail(entity.getEmail().getValue());
        dto.setTelefone(entity.getTelefone().getValue());
        dto.setCpf(entity.getCpf().getValue());
        dto.setDataNascimento(entity.getDataNascimento().getValue());
        return dto;
    }

    public PassageiroDTO modelToDTO(PassageiroModels entity) {
        PassageiroDTO dto = new PassageiroDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setTelefone(entity.getTelefone());
        dto.setCpf(entity.getCpf());
        dto.setDataNascimento(entity.getDataNascimento());
        return dto;
    }
    public List<PassageiroDTO> modelToDTOList(List<PassageiroModels> list) {
        return list.stream().map(this::modelToDTO).collect(Collectors.toList());
    }
    public List<PassageiroDTO> entityToDTOList(List<Passageiro> list) {
        return list.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public List<Passageiro> DTOtoEntityList(List<PassageiroDTO> list) {
        return list.stream().map(this::DTOtoEntity).collect(Collectors.toList());
    }

    public List<PassageiroModels> entityToModelList(List<Passageiro> list) {
        return list.stream().map(this::entityToModel).collect(Collectors.toList());
    }

    public List<Passageiro> modelToEntityList(List<PassageiroModels> list) {
        return list.stream().map(this::modelToEntity).collect(Collectors.toList());
    }
}
