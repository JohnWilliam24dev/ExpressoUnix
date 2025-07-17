package com.johnwilliam.ExpressoUnix.Entities.Mappers;

import com.johnwilliam.ExpressoUnix.Models.PassagemModels;
import com.johnwilliam.ExpressoUnix.Entities.Passagem;


public class PassagemMapper {

    public static Passagem toEntity(PassagemModels model) {
        return new Passagem(
            model.getId(),
            model.getStatus(),
            model.getIdViagem(),
            model.getIdAssento(),
            model.getIdPassageiro(),
            model.getDataPassagem(),
            model.getHoraPassagem(),
            model.getOrigem(),
            model.getDestino(),
            model.getDistancia(),
            model.getPreco()
        );
    }

    public static PassagemModels toModel(Passagem entity) {
        return new PassagemModels(
            entity.getStatus(),
            entity.getViagem(),
            entity.getAssento(),
            entity.getPassageiro(),
            entity.getDataPassagem(),
            entity.getHoraPassagem(),
            entity.getOrigem().getValue(),
            entity.getDestino().getValue(),
            entity.getDistancia(),
            entity.getPreco()
        );
    }
}
