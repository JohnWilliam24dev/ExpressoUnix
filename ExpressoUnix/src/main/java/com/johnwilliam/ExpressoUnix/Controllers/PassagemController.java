package com.johnwilliam.ExpressoUnix.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.johnwilliam.ExpressoUnix.DTO.PassagemDTO;
import com.johnwilliam.ExpressoUnix.Facade.PassagemFacade;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/passagem")
public class PassagemController {

    private final PassagemFacade passagemFacade;

    @Autowired
    public PassagemController(PassagemFacade passagemFacade) {
        this.passagemFacade = passagemFacade;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPassagem(@Valid @RequestBody PassagemDTO passagem) {
        passagem.setId(null); // criacao sempre gera um novo ID
        passagemFacade.createPassagem(passagem);
    }

    @GetMapping("/{id}")
    public PassagemDTO getPassagemById(@PathVariable long id) {
        return passagemFacade.getPassagemById(id);
    }

    @GetMapping
    public List<PassagemDTO> getAllPassagens() {
        return passagemFacade.getAllPassagem();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassagem(@PathVariable long id, @Valid @RequestBody PassagemDTO passagem) {
        passagem.setId(id); // o ID da URL e a fonte de verdade
        passagemFacade.updatePassagem(passagem);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePassagem(@PathVariable long id) {
        passagemFacade.deletePassagem(id);
    }
}
