package com.johnwilliam.ExpressoUnix.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.johnwilliam.ExpressoUnix.Entities.Funcionario;
import com.johnwilliam.ExpressoUnix.Facade.FuncionarioFacade;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    private final FuncionarioFacade funcionarioFacade;

    @Autowired
    public FuncionarioController(FuncionarioFacade funcionarioFacade) {
        this.funcionarioFacade = funcionarioFacade;
    }

    @PostMapping
    public void createFuncionario(@RequestBody Funcionario funcionario) {
        funcionarioFacade.createFuncionario(funcionario);
    }

    @GetMapping("/{id}")
    public Funcionario getFuncionarioById(@PathVariable long id) {
        return funcionarioFacade.getFuncionarioById(id);
    }

    @GetMapping
    public List<Funcionario> getAllFuncionarios() {
        return funcionarioFacade.getAllFuncionario();
    }

    @PutMapping("/{id}")
    public void updateFuncionario(@RequestBody Funcionario funcionario) {
        funcionarioFacade.updateFuncionario(funcionario);
    }

    @DeleteMapping("/{id}")
    public void deleteFuncionario(@PathVariable long id) {
        funcionarioFacade.deleteFuncionario(id);
    }
}
