package com.johnwilliam.ExpressoUnix.Applications;

import java.util.List;

import org.springframework.stereotype.Service;


import com.johnwilliam.ExpressoUnix.Models.FuncionarioModels;
import com.johnwilliam.ExpressoUnix.Repositories.*;

@Service
public class FuncionarioApplication {
    private FuncionarioRepository funcionarioRepository;
    public FuncionarioApplication(FuncionarioRepository funcionarioRepository){
        this.funcionarioRepository = funcionarioRepository;
    }
    public void createFuncionario(FuncionarioModels funcionario){
        funcionarioRepository.createFuncionario(funcionario);
    }
    public FuncionarioModels getFuncionarioById(long id) {
        return funcionarioRepository.getFuncionarioById(id);
    }
    public List<FuncionarioModels> getAllFuncionario() {
        return funcionarioRepository.getAllFuncionario();
    }
    public void updateFuncionario(FuncionarioModels funcionario) {
        funcionarioRepository.updateFuncionario( funcionario);
    }
    public void deleteFuncionario(long id) {
        funcionarioRepository.deleteFuncionario(id);
    }
}