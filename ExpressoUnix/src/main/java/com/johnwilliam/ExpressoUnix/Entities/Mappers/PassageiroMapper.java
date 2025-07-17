package com.johnwilliam.ExpressoUnix.Entities.Mappers;

import com.johnwilliam.ExpressoUnix.Models.PassageiroModels;
import com.johnwilliam.ExpressoUnix.Entities.Passageiro;

public class PassageiroMapper {

    public static Passageiro toEntity(PassageiroModels model) {
        return new Passageiro(
            model.getId(),
            model.getNome(),
            model.getEmail(),
            model.getTelefone(),
            model.getCpf(),
            model.getDataNascimento()
        );
    }

    public static PassageiroModels toModel(Passageiro entity) {
        return new PassageiroModels(
            entity.getNome().getValue(),
            entity.getEmail().getValue(),
            entity.getTelefone().getValue(),
            entity.getCpf().getValue(),
            entity.getDataNascimento().getValue()
        );
    }
}
