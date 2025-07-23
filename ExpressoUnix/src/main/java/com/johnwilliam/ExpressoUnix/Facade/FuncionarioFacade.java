package com.johnwilliam.ExpressoUnix.Facade;

import java.util.List;
import org.springframework.stereotype.Component;
import com.johnwilliam.ExpressoUnix.Applications.FuncionarioApplication;

import com.johnwilliam.ExpressoUnix.DTO.FuncionarioDTO;

@Component
public class FuncionarioFacade {
    private final FuncionarioApplication funcionarioApplication;
    
    public FuncionarioFacade(FuncionarioApplication funcionarioApplication) {
        this.funcionarioApplication = funcionarioApplication;
    }
    
    public void createFuncionario(FuncionarioDTO funcionario) {
        funcionarioApplication.createFuncionario(funcionario);
    }
    
    public FuncionarioDTO getFuncionarioById(long id) {
        return funcionarioApplication.getFuncionarioById(id);
    }
    
    public List<FuncionarioDTO> getAllFuncionario() {
        return funcionarioApplication.getAllFuncionario();
    }
    
    public void updateFuncionario( FuncionarioDTO funcionario) {
        funcionarioApplication.updateFuncionario( funcionario);
    }
    
    public void deleteFuncionario(long id) {
        funcionarioApplication.deleteFuncionario(id);
    }
}
