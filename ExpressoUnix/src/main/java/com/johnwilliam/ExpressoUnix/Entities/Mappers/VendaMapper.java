package com.johnwilliam.ExpressoUnix.Entities.Mappers;

import com.johnwilliam.ExpressoUnix.Models.VendaModels;
import com.johnwilliam.ExpressoUnix.Entities.Venda;

public class VendaMapper {

    public static Venda toEntity(VendaModels model) {
        return new Venda(
            model.getId(),
            model.getIdFuncionario(),
            model.getIdPassagem(),
            model.getHorarioEmissao()
        );
    }

    public static VendaModels toModel(Venda entity) {
        VendaModels model = new VendaModels();
        model.setId(entity.getId());
        model.setIdFuncionario(entity.getFuncionario());
        model.setIdPassagem(entity.getPassagem());
        model.setHorarioEmissao(entity.getHorarioEmissao());
        return model;
    }
}
