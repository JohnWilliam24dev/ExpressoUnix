package com.johnwilliam.ExpressoUnix.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.johnwilliam.ExpressoUnix.DTO.FuncionarioDTO;
import com.johnwilliam.ExpressoUnix.Facade.FuncionarioFacade;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    private final FuncionarioFacade funcionarioFacade;

    @Autowired
    public FuncionarioController(FuncionarioFacade funcionarioFacade) {
        this.funcionarioFacade = funcionarioFacade;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createFuncionario(@Valid @RequestBody FuncionarioDTO funcionario) {
        funcionario.setId(null); // criacao sempre gera um novo ID
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateFuncionario(@PathVariable long id, @Valid @RequestBody FuncionarioDTO funcionario) {
        funcionario.setId(id); // o ID da URL e a fonte de verdade
        funcionarioFacade.updateFuncionario(funcionario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFuncionario(@PathVariable long id) {
        funcionarioFacade.deleteFuncionario(id);
    }
}
