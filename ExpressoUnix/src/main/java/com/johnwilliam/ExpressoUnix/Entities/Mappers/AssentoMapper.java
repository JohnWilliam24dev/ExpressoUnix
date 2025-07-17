package com.johnwilliam.ExpressoUnix.Entities.Mappers;

import com.johnwilliam.ExpressoUnix.Models.AssentoModels;
import com.johnwilliam.ExpressoUnix.Entities.Assento;


public class AssentoMapper {

    public static Assento toEntity(AssentoModels model) {
        return new Assento(
            model.getId(),
            model.getIdViagem(),
            model.getNumeroAssento(),
            model.getStatusAssento()
        );
    }

    public static AssentoModels toModel(Assento entity) {
        AssentoModels model = new AssentoModels();
        model.setId(entity.getId());
        model.setIdViagem(entity.getViagem());
        model.setNumeroAssento(entity.getNumeroAssento());
        model.setStatusAssento(entity.getStatusAssento());
        return model;
    }
}
