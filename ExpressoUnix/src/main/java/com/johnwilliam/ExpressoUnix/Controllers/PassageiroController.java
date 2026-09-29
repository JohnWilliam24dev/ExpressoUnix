package com.johnwilliam.ExpressoUnix.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.johnwilliam.ExpressoUnix.DTO.PassageiroDTO;
import com.johnwilliam.ExpressoUnix.Facade.PassageiroFacade;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/passageiro")
public class PassageiroController {

    private final PassageiroFacade passageiroFacade;

    @Autowired
    public PassageiroController(PassageiroFacade passageiroFacade) {
        this.passageiroFacade = passageiroFacade;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPassageiro(@Valid @RequestBody PassageiroDTO passageiro) {
        passageiro.setId(null); // criacao sempre gera um novo ID
        passageiroFacade.createPassageiro(passageiro);
    }

    @GetMapping("/{id}")
    public PassageiroDTO getPassageiroById(@PathVariable long id) {
        return passageiroFacade.getPassageiroById(id);
    }

    @GetMapping
    public List<PassageiroDTO> getAllPassageiros() {
        return passageiroFacade.getAllPassageiro();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassageiro(@PathVariable long id, @Valid @RequestBody PassageiroDTO passageiro) {
        passageiro.setId(id); // o ID da URL e a fonte de verdade
        passageiroFacade.updatePassageiro(passageiro);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePassageiro(@PathVariable long id) {
        passageiroFacade.deletePassageiro(id);
    }
}
