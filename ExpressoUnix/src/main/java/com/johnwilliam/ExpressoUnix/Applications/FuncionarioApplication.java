package com.johnwilliam.ExpressoUnix.Applications;

import java.util.List;

import org.springframework.stereotype.Service;


import com.johnwilliam.ExpressoUnix.DTO.FuncionarioDTO;
import com.johnwilliam.ExpressoUnix.Entities.Funcionario;
import com.johnwilliam.ExpressoUnix.Mappers.FuncionarioMapper;
import com.johnwilliam.ExpressoUnix.Repositories.*;

@Service
public class FuncionarioApplication {
    private FuncionarioRepository funcionarioRepository;
    private FuncionarioMapper funcionarioMapper;
    public FuncionarioApplication(FuncionarioRepository funcionarioRepository, FuncionarioMapper funcionarioMapper){
        this.funcionarioRepository = funcionarioRepository;
        this.funcionarioMapper = funcionarioMapper;
    }
    public void createFuncionario(FuncionarioDTO funcionario){
        Funcionario entity=funcionarioMapper.DTOtoEntity(funcionario);
        funcionarioRepository.createFuncionario(funcionarioMapper.entityToModel(entity));
    }
    public FuncionarioDTO getFuncionarioById(long id) {
        return funcionarioMapper.entityToDTO(funcionarioMapper.modelToEntity(funcionarioRepository.getFuncionarioById(id)));
    }
    public List<FuncionarioDTO> getAllFuncionario() {
        return funcionarioMapper.modelToDTOList(funcionarioRepository.getAllFuncionario()); 
    }
    public void updateFuncionario(FuncionarioDTO funcionario) {
        Funcionario entity=funcionarioMapper.DTOtoEntity(funcionario);
        funcionarioRepository.updateFuncionario( funcionarioMapper.entityToModel(entity));
    }
    public void deleteFuncionario(long id) {
        funcionarioRepository.deleteFuncionario(id);
    }
}