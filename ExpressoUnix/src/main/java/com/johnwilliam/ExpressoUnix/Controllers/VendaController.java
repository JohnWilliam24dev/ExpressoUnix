package com.johnwilliam.ExpressoUnix.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.johnwilliam.ExpressoUnix.DTO.VendaDTO;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createVenda(@Valid @RequestBody VendaDTO venda) {
        venda.setId(null); // criacao sempre gera um novo ID
        vendaFacade.createVenda(venda);
    }

    @GetMapping("/{id}")
    public VendaDTO getVendaById(@PathVariable long id) {
        return vendaFacade.getVendaById(id);
    }

    @GetMapping
    public List<VendaDTO> getAllVendas() {
        return vendaFacade.getAllVenda();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateVenda(@PathVariable long id, @Valid @RequestBody VendaDTO venda) {
        venda.setId(id); // o ID da URL e a fonte de verdade
        vendaFacade.updateVenda(venda);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVenda(@PathVariable long id) {
        vendaFacade.deleteVenda(id);
    }
}
