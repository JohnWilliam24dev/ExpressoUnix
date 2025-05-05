package com.johnwilliam.ExpressoUnix.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.johnwilliam.ExpressoUnix.Facade.VeiculoFacade;
import com.johnwilliam.ExpressoUnix.Models.VeiculoModels;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    private final VeiculoFacade veiculoFacade;

    @Autowired
    public VeiculoController(VeiculoFacade veiculoFacade) {
        this.veiculoFacade = veiculoFacade;
    }

    @PostMapping
    public void createVeiculo(@RequestBody VeiculoModels veiculo) {
        veiculoFacade.createVeiculo(veiculo);
    }

    @GetMapping("/{id}")
    public VeiculoModels getVeiculoById(@PathVariable long id) {
        return veiculoFacade.getVeiculoById(id);
    }

    @GetMapping
    public List<VeiculoModels> getAllVeiculos() {
        return veiculoFacade.getAllVeiculo();
    }

    @PutMapping("/{id}")
    public void updateVeiculo(@RequestBody VeiculoModels veiculo) {
        veiculoFacade.updateVeiculo(veiculo);
    }

    @DeleteMapping("/{id}")
    public void deleteVeiculo(@PathVariable long id) {
        veiculoFacade.deleteVeiculo(id);
    }
}
