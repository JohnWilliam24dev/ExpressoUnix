package com.johnwilliam.ExpressoUnix.Facade;

import java.util.List;
import org.springframework.stereotype.Component;
import com.johnwilliam.ExpressoUnix.Applications.FuncionarioApplication;
import com.johnwilliam.ExpressoUnix.Entities.Funcionario;

@Component
public class FuncionarioFacade {
    private final FuncionarioApplication funcionarioApplication;
    
    public FuncionarioFacade(FuncionarioApplication funcionarioApplication) {
        this.funcionarioApplication = funcionarioApplication;
    }
    
    public void createFuncionario(Funcionario funcionario) {
        funcionarioApplication.createFuncionario(funcionario);
    }
    
    public Funcionario getFuncionarioById(long id) {
        return funcionarioApplication.getFuncionarioById(id);
    }
    
    public List<Funcionario> getAllFuncionario() {
        return funcionarioApplication.getAllFuncionario();
    }
    
    public void updateFuncionario( Funcionario funcionario) {
        funcionarioApplication.updateFuncionario( funcionario);
    }
    
    public void deleteFuncionario(long id) {
        funcionarioApplication.deleteFuncionario(id);
    }
}
