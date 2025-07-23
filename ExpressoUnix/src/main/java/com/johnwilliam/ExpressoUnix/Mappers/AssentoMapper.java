package com.johnwilliam.ExpressoUnix.Mappers;

import com.johnwilliam.ExpressoUnix.Models.AssentoModels;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.johnwilliam.ExpressoUnix.DTO.AssentoDTO;
import com.johnwilliam.ExpressoUnix.Entities.Assento;

@Component
public class AssentoMapper {

    public Assento DTOtoEntity(AssentoDTO DTO) {
        return new Assento(
            DTO.getId(),
            DTO.getIdViagem(),
            DTO.getNumeroAssento(),
            DTO.getStatusAssento()
        );
    }
    public Assento modeltoEntity(AssentoModels model) {
        return new Assento(
            model.getId(),
            model.getIdViagem(),
            model.getNumeroAssento(),
            model.getStatusAssento()
        );
    }
    public AssentoModels entityToModel(Assento entity) {
        AssentoModels model = new AssentoModels();
        model.setId(entity.getId());
        model.setIdViagem(entity.getViagem());
        model.setNumeroAssento(entity.getNumeroAssento());
        model.setStatusAssento(entity.getStatusAssento());
        return model;
    }
    public AssentoDTO entityToDTO(Assento entity) {
        AssentoDTO DTO = new AssentoDTO();
        DTO.setId(entity.getId());
        DTO.setIdViagem(entity.getViagem());
        DTO.setNumeroAssento(entity.getNumeroAssento());
        DTO.setStatusAssento(entity.getStatusAssento());
        return DTO;
    }
    public AssentoDTO modelToDTO(AssentoModels model) {
        AssentoDTO DTO = new AssentoDTO();
        DTO.setId(model.getId());
        DTO.setIdViagem(model.getIdViagem());
        DTO.setNumeroAssento(model.getNumeroAssento());
        DTO.setStatusAssento(model.getStatusAssento());
        return DTO;
    }
    public List<AssentoDTO> entityToDTOList(List<Assento> list){
        return list.stream().map(this::entityToDTO).collect(Collectors.toList());

    }
    public List<AssentoDTO> modelToDTOList(List<AssentoModels> list){
        return list.stream().map(this::modelToDTO).collect(Collectors.toList());

    }
    public List<Assento> modeltoEntityList(List<AssentoModels> list){
        return list.stream().map(this::modeltoEntity).collect(Collectors.toList());
    }
    public List<Assento> DTOtoEntityList(List<AssentoDTO> list){
        return list.stream().map(this::DTOtoEntity).collect(Collectors.toList());
    }
    public List<AssentoModels> entityToModelList(List<Assento> list){
        return list.stream().map(this::entityToModel).collect(Collectors.toList());
    } 
}
