package com.johnwilliam.ExpressoUnix.Entities.Mappers;

import com.johnwilliam.ExpressoUnix.Models.VeiculoModels;
import com.johnwilliam.ExpressoUnix.Entities.Veiculo;

public class VeiculoMapper {

    public static Veiculo toEntity(VeiculoModels model) {
        return new Veiculo(
            model.getId(),
            model.getClasse(),
            model.getCapacidade(),
            model.getStatusVeiculo()
        );
    }

    public static VeiculoModels toModel(Veiculo entity) {
        VeiculoModels model = new VeiculoModels();
        model.setId(entity.getId());
        model.setClasse(entity.getClasse());
        model.setCapacidade(entity.getCapacidade());
        model.setStatusVeiculo(entity.getStatusVeiculo());
        return model;
    }
}
