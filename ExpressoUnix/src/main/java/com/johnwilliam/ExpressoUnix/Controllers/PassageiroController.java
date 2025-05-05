package com.johnwilliam.ExpressoUnix.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.johnwilliam.ExpressoUnix.Facade.PassageiroFacade;
import com.johnwilliam.ExpressoUnix.Models.PassageiroModels;

@RestController
@RequestMapping("/passageiro")
public class PassageiroController {

    private final PassageiroFacade passageiroFacade;

    @Autowired
    public PassageiroController(PassageiroFacade passageiroFacade) {
        this.passageiroFacade = passageiroFacade;
    }

    @PostMapping
    public void createPassageiro(@RequestBody PassageiroModels passageiro) {
        passageiroFacade.createPassageiro(passageiro);
    }

    @GetMapping("/{id}")
    public PassageiroModels getPassageiroById(@PathVariable long id) {
        return passageiroFacade.getPassageiroById(id);
    }

    @GetMapping
    public List<PassageiroModels> getAllPassageiros() {
        return passageiroFacade.getAllPassageiro();
    }

    @PutMapping("/{id}")
    public void updatePassageiro(@RequestBody PassageiroModels passageiro) {
        passageiroFacade.updatePassageiro(passageiro);
    }

    @DeleteMapping("/{id}")
    public void deletePassageiro(@PathVariable long id) {
        passageiroFacade.deletePassageiro(id);
    }
}
