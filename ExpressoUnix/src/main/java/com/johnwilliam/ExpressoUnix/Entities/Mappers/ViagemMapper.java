package com.johnwilliam.ExpressoUnix.Entities.Mappers;

import com.johnwilliam.ExpressoUnix.Models.VeiculoModels;
import com.johnwilliam.ExpressoUnix.Models.ViagemModels;
import com.johnwilliam.ExpressoUnix.Entities.Viagem;

public class ViagemMapper {
    

    public static Viagem toEntity(ViagemModels model,VeiculoModels veiculoModel) {
      
    
        return new Viagem(
            model.getId(),
            veiculoModel,
            model.getDataViagem(),
            model.getHoraViagem(),
            model.getOrigem(),
            model.getDestino()
        );
    }

    public static ViagemModels toModel(Viagem entity) {
        ViagemModels model = new ViagemModels();
        model.setId(entity.getId());
        model.setVeiculo(entity.getVeiculo());
        model.setDataViagem(entity.getDataViagem());
        model.setHoraViagem(entity.getHoraViagem());
        model.setOrigem(entity.getOrigem().getValue());
        model.setDestino(entity.getDestino().getValue());
        return model;
    }
}
