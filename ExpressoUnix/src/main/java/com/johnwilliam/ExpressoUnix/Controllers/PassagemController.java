package com.johnwilliam.ExpressoUnix.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.johnwilliam.ExpressoUnix.Entities.Passagem;
import com.johnwilliam.ExpressoUnix.Facade.PassagemFacade;

@RestController
@RequestMapping("/passagem")
public class PassagemController {

    private final PassagemFacade passagemFacade;

    @Autowired
    public PassagemController(PassagemFacade passagemFacade) {
        this.passagemFacade = passagemFacade;
    }

    @PostMapping
    public void createPassagem(@RequestBody Passagem passagem) {
        passagemFacade.createPassagem(passagem);
    }

    @GetMapping("/{id}")
    public Passagem getPassagemById(@PathVariable long id) {
        return passagemFacade.getPassagemById(id);
    }

    @GetMapping
    public List<Passagem> getAllPassagens() {
        return passagemFacade.getAllPassagem();
    }

    @PutMapping("/{id}")
    public void updatePassagem(@RequestBody Passagem passagem) {
        passagemFacade.updatePassagem(passagem);
    }

    @DeleteMapping("/{id}")
    public void deletePassagem(@PathVariable long id) {
        passagemFacade.deletePassagem(id);
    }
}
