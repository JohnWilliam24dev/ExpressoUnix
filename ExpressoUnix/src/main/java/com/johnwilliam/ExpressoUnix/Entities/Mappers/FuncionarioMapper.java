package com.johnwilliam.ExpressoUnix.Entities.Mappers;

import com.johnwilliam.ExpressoUnix.Entities.Funcionario;
import com.johnwilliam.ExpressoUnix.Models.FuncionarioModels;

public class FuncionarioMapper {
    public Funcionario toEntitie(FuncionarioModels model){
        Funcionario funcionario = new Funcionario(
            model.getNome(),
            model.getEmail(),
            model.getTelefone(),
            model.getCpf(),
            model.getDataNascimento(),
            model.getCargo()
        );
        return funcionario;
                
    } 
    public static FuncionarioModels toModel(Funcionario entity) {
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

}
