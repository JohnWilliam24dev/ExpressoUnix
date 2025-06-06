package com.johnwilliam.ExpressoUnix.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.johnwilliam.ExpressoUnix.Facade.VendaFacade;
import com.johnwilliam.ExpressoUnix.Models.VendaModels;

@RestController
@RequestMapping("/venda")
public class VendaController {

    private final VendaFacade vendaFacade;

    @Autowired
    public VendaController(VendaFacade vendaFacade) {
        this.vendaFacade = vendaFacade;
    }

    @PostMapping
    public void createVenda(@RequestBody VendaModels venda) {
        vendaFacade.createVenda(venda);
    }

    @GetMapping("/{id}")
    public VendaModels getVendaById(@PathVariable long id) {
        return vendaFacade.getVendaById(id);
    }

    @GetMapping
    public List<VendaModels> getAllVendas() {
        return vendaFacade.getAllVenda();
    }

    @PutMapping("/{id}")
    public void updateVenda(@RequestBody VendaModels venda) {
        vendaFacade.updateVenda(venda);
    }

    @DeleteMapping("/{id}")
    public void deleteVenda(@PathVariable long id) {
        vendaFacade.deleteVenda(id);
    }
}
