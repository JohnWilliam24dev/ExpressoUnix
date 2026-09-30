package com.johnwilliam.ExpressoUnix.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.johnwilliam.ExpressoUnix.DTO.PassagemDTO;
import com.johnwilliam.ExpressoUnix.Facade.PassagemFacade;

/** Somente consulta: passagens sao emitidas por POST /venda. */
@RestController
@RequestMapping("/passagem")
public class PassagemController {

    private final PassagemFacade passagemFacade;

    @Autowired
    public PassagemController(PassagemFacade passagemFacade) {
        this.passagemFacade = passagemFacade;
    }

    @GetMapping("/{id}")
    public PassagemDTO getPassagemById(@PathVariable long id) {
        return passagemFacade.getPassagemById(id);
    }

    @GetMapping
    public List<PassagemDTO> getAllPassagens() {
        return passagemFacade.getAllPassagem();
    }
}
