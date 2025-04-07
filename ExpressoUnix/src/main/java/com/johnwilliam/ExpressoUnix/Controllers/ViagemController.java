package com.johnwilliam.ExpressoUnix.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.johnwilliam.ExpressoUnix.Entities.Viagem;
import com.johnwilliam.ExpressoUnix.Facade.ViagemFacade;

@RestController
@RequestMapping("/viagem")
public class ViagemController {

    private final ViagemFacade viagemFacade;

    @Autowired
    public ViagemController(ViagemFacade viagemFacade) {
        this.viagemFacade = viagemFacade;
    }

    @PostMapping
    public void createViagem(@RequestBody Viagem viagem) {
        viagemFacade.createViagem(viagem);
    }

    @GetMapping("/{id}")
    public Viagem getViagemById(@PathVariable long id) {
        return viagemFacade.getViagemById(id);
    }

    @GetMapping
    public List<Viagem> getAllViagens() {
        return viagemFacade.getAllViagem();
    }

    @PutMapping("/{id}")
    public void updateViagem(@RequestBody Viagem viagem) {
        viagemFacade.updateViagem(viagem);
    }

    @DeleteMapping("/{id}")
    public void deleteViagem(@PathVariable long id) {
        viagemFacade.deleteViagem(id);
    }
}
