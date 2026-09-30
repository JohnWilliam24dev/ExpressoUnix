package com.johnwilliam.ExpressoUnix.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.johnwilliam.ExpressoUnix.DTO.VendaDTO;
import com.johnwilliam.ExpressoUnix.DTO.VendaRequestDTO;
import com.johnwilliam.ExpressoUnix.Facade.VendaFacade;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/venda")
public class VendaController {

    private final VendaFacade vendaFacade;

    @Autowired
    public VendaController(VendaFacade vendaFacade) {
        this.vendaFacade = vendaFacade;
    }

    /** Devolve a venda criada com suas passagens (IDs, precos e status). */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendaDTO createVenda(@Valid @RequestBody VendaRequestDTO venda) {
        return vendaFacade.createVenda(venda);
    }

    @GetMapping("/{id}")
    public VendaDTO getVendaById(@PathVariable long id) {
        return vendaFacade.getVendaById(id);
    }

    @GetMapping
    public List<VendaDTO> getAllVendas() {
        return vendaFacade.getAllVenda();
    }
}
