package com.johnwilliam.ExpressoUnix.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.johnwilliam.ExpressoUnix.Facade.PassagemFacade;
import com.johnwilliam.ExpressoUnix.DTO.PassagemDTO;

@RestController
@RequestMapping("/passagem")
public class PassagemController {

    private final PassagemFacade passagemFacade;

    @Autowired
    public PassagemController(PassagemFacade passagemFacade) {
        this.passagemFacade = passagemFacade;
    }

    @PostMapping
    public void createPassagem(@RequestBody PassagemDTO passagem) {
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
    public void updatePassagem(@RequestBody PassagemDTO passagem) {
        passagemFacade.updatePassagem(passagem);
    }

    @DeleteMapping("/{id}")
    public void deletePassagem(@PathVariable long id) {
        passagemFacade.deletePassagem(id);
    }
}
