package com.johnwilliam.ExpressoUnix.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.johnwilliam.ExpressoUnix.Entities.Passageiro;
import com.johnwilliam.ExpressoUnix.Facade.PassageiroFacade;

@RestController
@RequestMapping("/passageiro")
public class PassageiroController {

    private final PassageiroFacade passageiroFacade;

    @Autowired
    public PassageiroController(PassageiroFacade passageiroFacade) {
        this.passageiroFacade = passageiroFacade;
    }

    @PostMapping
    public void createPassageiro(@RequestBody Passageiro passageiro) {
        passageiroFacade.createPassageiro(passageiro);
    }

    @GetMapping("/{id}")
    public Passageiro getPassageiroById(@PathVariable long id) {
        return passageiroFacade.getPassageiroById(id);
    }

    @GetMapping
    public List<Passageiro> getAllPassageiros() {
        return passageiroFacade.getAllPassageiro();
    }

    @PutMapping("/{id}")
    public void updatePassageiro(@RequestBody Passageiro passageiro) {
        passageiroFacade.updatePassageiro(passageiro);
    }

    @DeleteMapping("/{id}")
    public void deletePassageiro(@PathVariable long id) {
        passageiroFacade.deletePassageiro(id);
    }
}
