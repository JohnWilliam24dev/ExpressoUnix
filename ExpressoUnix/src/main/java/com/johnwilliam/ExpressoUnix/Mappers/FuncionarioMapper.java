
package com.johnwilliam.ExpressoUnix.Mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.johnwilliam.ExpressoUnix.DTO.FuncionarioDTO;
import com.johnwilliam.ExpressoUnix.Entities.Funcionario;
import com.johnwilliam.ExpressoUnix.Models.FuncionarioModels;

@Component
public class FuncionarioMapper {

    public Funcionario DTOtoEntity(FuncionarioDTO dto) {
        return new Funcionario(
            dto.getNome(),
            dto.getEmail(),
            dto.getTelefone(),
            dto.getCpf(),
            dto.getDataNascimento(),
            dto.getCargo()
        );
    }

    public FuncionarioModels entityToModel(Funcionario entity) {
        FuncionarioModels model = new FuncionarioModels(
            entity.getNome().getValue(),
            entity.getEmail().getValue(),
            entity.getTelefone().getValue(),
            entity.getCpf().getValue(),
            entity.getDataNascimento().getValue(),
            entity.getCargo().getValue()
        );
        model.setId(entity.getId());
        return model;
    }

    public FuncionarioDTO entityToDTO(Funcionario entity) {
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome().getValue());
        dto.setEmail(entity.getEmail().getValue());
        dto.setTelefone(entity.getTelefone().getValue());
        dto.setCpf(entity.getCpf().getValue());
        dto.setDataNascimento(entity.getDataNascimento().getValue());
        dto.setCargo(entity.getCargo().getValue());
        return dto;
    }

    public Funcionario modelToEntity(FuncionarioModels model) {
        Funcionario entity = new Funcionario(
            model.getNome(),
            model.getEmail(),
            model.getTelefone(),
            model.getCpf(),
            model.getDataNascimento(),
            model.getCargo()
        );
        entity.setId(model.getId());
        return entity;
    }
    public FuncionarioDTO modelToDTO(FuncionarioModels model) {
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setId(model.getId());
        dto.setNome(model.getNome());
        dto.setEmail(model.getEmail());
        dto.setTelefone(model.getTelefone());
        dto.setCpf(model.getCpf());
        dto.setDataNascimento(model.getDataNascimento());
        dto.setCargo(model.getCargo());
        return dto;
    }

    // List converters
    public List<FuncionarioDTO> entityToDTOList(List<Funcionario> list) {
        return list.stream().map(this::entityToDTO).collect(Collectors.toList());
    }
    public List<FuncionarioDTO> modelToDTOList(List<FuncionarioModels> list) {
        return list.stream().map(this::modelToDTO).collect(Collectors.toList());
    }
    public List<Funcionario> DTOtoEntityList(List<FuncionarioDTO> list) {
        return list.stream().map(this::DTOtoEntity).collect(Collectors.toList());
    }

    public List<FuncionarioModels> entityToModelList(List<Funcionario> list) {
        return list.stream().map(this::entityToModel).collect(Collectors.toList());
    }

    public List<Funcionario> modelToEntityList(List<FuncionarioModels> list) {
        return list.stream().map(this::modelToEntity).collect(Collectors.toList());
    }
}
