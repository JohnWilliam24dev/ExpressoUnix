package com.johnwilliam.ExpressoUnix.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.johnwilliam.ExpressoUnix.DTO.AssentoDTO;
import com.johnwilliam.ExpressoUnix.Facade.AssentoFacade;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/assento")
public class AssentoController {

    private final AssentoFacade assentoFacade;

    @Autowired
    public AssentoController(AssentoFacade assentoFacade) {
        this.assentoFacade = assentoFacade;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAssento(@Valid @RequestBody AssentoDTO assento) {
        assento.setId(null); // criacao sempre gera um novo ID
        assentoFacade.createAssento(assento);
    }

    @GetMapping("/{id}")
    public AssentoDTO getAssentoById(@PathVariable long id) {
        return assentoFacade.getAssentoById(id);
    }

    @GetMapping
    public List<AssentoDTO> getAllAssentos() {
        return assentoFacade.getAllAssento();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAssento(@PathVariable long id, @Valid @RequestBody AssentoDTO assento) {
        assento.setId(id); // o ID da URL e a fonte de verdade
        assentoFacade.updateAssento(assento);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAssento(@PathVariable long id) {
        assentoFacade.deleteAssento(id);
    }
}
