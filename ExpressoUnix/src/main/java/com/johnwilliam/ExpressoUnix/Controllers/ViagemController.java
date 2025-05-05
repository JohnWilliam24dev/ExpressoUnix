package com.johnwilliam.ExpressoUnix.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.johnwilliam.ExpressoUnix.Facade.ViagemFacade;
import com.johnwilliam.ExpressoUnix.Models.ViagemModels;

@RestController
@RequestMapping("/viagem")
public class ViagemController {

    private final ViagemFacade viagemFacade;

    @Autowired
    public ViagemController(ViagemFacade viagemFacade) {
        this.viagemFacade = viagemFacade;
    }

    @PostMapping
    public void createViagem(@RequestBody ViagemModels viagem) {
        viagemFacade.createViagem(viagem);
    }

    @GetMapping("/{id}")
    public ViagemModels getViagemById(@PathVariable long id) {
        return viagemFacade.getViagemById(id);
    }

    @GetMapping
    public List<ViagemModels> getAllViagens() {
        return viagemFacade.getAllViagem();
    }

    @PutMapping("/{id}")
    public void updateViagem(@RequestBody ViagemModels viagem) {
        viagemFacade.updateViagem(viagem);
    }

    @DeleteMapping("/{id}")
    public void deleteViagem(@PathVariable long id) {
        viagemFacade.deleteViagem(id);
    }
}
