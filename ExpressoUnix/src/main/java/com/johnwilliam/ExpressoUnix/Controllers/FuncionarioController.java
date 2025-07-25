package com.johnwilliam.ExpressoUnix.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.johnwilliam.ExpressoUnix.Facade.FuncionarioFacade;
import com.johnwilliam.ExpressoUnix.DTO.FuncionarioDTO;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    private final FuncionarioFacade funcionarioFacade;

    @Autowired
    public FuncionarioController(FuncionarioFacade funcionarioFacade) {
        this.funcionarioFacade = funcionarioFacade;
    }

    @PostMapping
    public void createFuncionario(@RequestBody FuncionarioDTO funcionario) {
        funcionarioFacade.createFuncionario(funcionario);
    }

    @GetMapping("/{id}")
    public FuncionarioDTO getFuncionarioById(@PathVariable long id) {
        return funcionarioFacade.getFuncionarioById(id);
    }

    @GetMapping
    public List<FuncionarioDTO> getAllFuncionarios() {
        return funcionarioFacade.getAllFuncionario();
    }

    @PutMapping("/{id}")
    public void updateFuncionario(@RequestBody FuncionarioDTO funcionario) {
        funcionarioFacade.updateFuncionario(funcionario);
    }

    @DeleteMapping("/{id}")
    public void deleteFuncionario(@PathVariable long id) {
        funcionarioFacade.deleteFuncionario(id);
    }
}
