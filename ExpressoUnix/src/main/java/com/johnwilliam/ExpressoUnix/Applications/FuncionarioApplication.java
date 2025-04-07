package com.johnwilliam.ExpressoUnix.Applications;

import java.util.List;

import org.springframework.stereotype.Service;

import com.johnwilliam.ExpressoUnix.Entities.*;
import com.johnwilliam.ExpressoUnix.Repositories.*;

@Service
public class FuncionarioApplication {
    private FuncionarioRepository funcionarioRepository;
    public FuncionarioApplication(FuncionarioRepository funcionarioRepository){
        this.funcionarioRepository = funcionarioRepository;
    }
    public void createFuncionario(Funcionario funcionario){
        funcionarioRepository.createFuncionario(funcionario);
    }
    public Funcionario getFuncionarioById(long id) {
        return funcionarioRepository.getFuncionarioById(id);
    }
    public List<Funcionario> getAllFuncionario() {
        return funcionarioRepository.getAllFuncionario();
    }
    public void updateFuncionario(Funcionario funcionario) {
        funcionarioRepository.updateFuncionario( funcionario);
    }
    public void deleteFuncionario(long id) {
        funcionarioRepository.deleteFuncionario(id);
    }
}