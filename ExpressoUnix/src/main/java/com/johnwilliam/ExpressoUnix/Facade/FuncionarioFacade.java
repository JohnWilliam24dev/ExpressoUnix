package com.johnwilliam.ExpressoUnix.Facade;

import java.util.List;
import org.springframework.stereotype.Component;
import com.johnwilliam.ExpressoUnix.Applications.FuncionarioApplication;

import com.johnwilliam.ExpressoUnix.Models.FuncionarioModels;

@Component
public class FuncionarioFacade {
    private final FuncionarioApplication funcionarioApplication;
    
    public FuncionarioFacade(FuncionarioApplication funcionarioApplication) {
        this.funcionarioApplication = funcionarioApplication;
    }
    
    public void createFuncionario(FuncionarioModels funcionario) {
        funcionarioApplication.createFuncionario(funcionario);
    }
    
    public FuncionarioModels getFuncionarioById(long id) {
        return funcionarioApplication.getFuncionarioById(id);
    }
    
    public List<FuncionarioModels> getAllFuncionario() {
        return funcionarioApplication.getAllFuncionario();
    }
    
    public void updateFuncionario( FuncionarioModels funcionario) {
        funcionarioApplication.updateFuncionario( funcionario);
    }
    
    public void deleteFuncionario(long id) {
        funcionarioApplication.deleteFuncionario(id);
    }
}
