package com.johnwilliam.ExpressoUnix.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.johnwilliam.ExpressoUnix.DTO.ViagemDTO;
import com.johnwilliam.ExpressoUnix.Facade.ViagemFacade;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/viagem")
public class ViagemController {

    private final ViagemFacade viagemFacade;

    @Autowired
    public ViagemController(ViagemFacade viagemFacade) {
        this.viagemFacade = viagemFacade;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createViagem(@Valid @RequestBody ViagemDTO viagem) {
        viagem.setId(null); // criacao sempre gera um novo ID
        viagemFacade.createViagem(viagem);
    }

    @GetMapping("/{id}")
    public ViagemDTO getViagemById(@PathVariable long id) {
        return viagemFacade.getViagemById(id);
    }

    @GetMapping
    public List<ViagemDTO> getAllViagens() {
        return viagemFacade.getAllViagem();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateViagem(@PathVariable long id, @Valid @RequestBody ViagemDTO viagem) {
        viagem.setId(id); // o ID da URL e a fonte de verdade
        viagemFacade.updateViagem(viagem);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteViagem(@PathVariable long id) {
        viagemFacade.deleteViagem(id);
    }
}
