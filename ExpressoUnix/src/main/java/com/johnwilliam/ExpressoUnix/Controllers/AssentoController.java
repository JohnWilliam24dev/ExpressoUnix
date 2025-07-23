package com.johnwilliam.ExpressoUnix.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.johnwilliam.ExpressoUnix.DTO.AssentoDTO;
import com.johnwilliam.ExpressoUnix.Facade.AssentoFacade;


@RestController
@RequestMapping("/assento")
public class AssentoController {

    private final AssentoFacade assentoFacade;

    @Autowired
    public AssentoController(AssentoFacade assentoFacade) {
        this.assentoFacade = assentoFacade;
    }

    @PostMapping
    public void createAssento(@RequestBody AssentoDTO assento) {
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
    public void updateAssento(@RequestBody AssentoDTO assento) {
        assentoFacade.updateAssento( assento);
    }

    @DeleteMapping("/{id}")
    public void deleteAssento(@PathVariable long id) {
        assentoFacade.deleteAssento(id);
    }
}

