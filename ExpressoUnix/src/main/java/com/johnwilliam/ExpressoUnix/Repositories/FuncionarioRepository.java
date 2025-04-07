package com.johnwilliam.ExpressoUnix.Repositories;

import java.util.List;


import org.springframework.stereotype.Repository;

import com.johnwilliam.ExpressoUnix.Entities.Funcionario;
import com.johnwilliam.ExpressoUnix.Repositories.JPA.FuncionarioJPA;
@Repository
public class FuncionarioRepository {
    private final FuncionarioJPA funcionarioJPA;

    public FuncionarioRepository(FuncionarioJPA funcionarioJPA) {
        this.funcionarioJPA = funcionarioJPA;
    }
    public void createFuncionario(Funcionario funcionario){
        funcionarioJPA.save(funcionario);
    }
    public Funcionario getFuncionarioById(long id){
        return funcionarioJPA.findById(id).get();
    }
    public List<Funcionario> getAllFuncionario(){
        return funcionarioJPA.findAll();
    }
    public void updateFuncionario( Funcionario funcionario){
       
       this.funcionarioJPA.save(funcionario);
    }
    public void deleteFuncionario(long id){
        funcionarioJPA.deleteById(id);
    }

}
