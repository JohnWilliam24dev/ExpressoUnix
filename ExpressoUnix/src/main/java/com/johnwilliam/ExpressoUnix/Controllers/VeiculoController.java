package com.johnwilliam.ExpressoUnix.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.johnwilliam.ExpressoUnix.Entities.Veiculo;
import com.johnwilliam.ExpressoUnix.Facade.VeiculoFacade;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    private final VeiculoFacade veiculoFacade;

    @Autowired
    public VeiculoController(VeiculoFacade veiculoFacade) {
        this.veiculoFacade = veiculoFacade;
    }

    @PostMapping
    public void createVeiculo(@RequestBody Veiculo veiculo) {
        veiculoFacade.createVeiculo(veiculo);
    }

    @GetMapping("/{id}")
    public Veiculo getVeiculoById(@PathVariable long id) {
        return veiculoFacade.getVeiculoById(id);
    }

    @GetMapping
    public List<Veiculo> getAllVeiculos() {
        return veiculoFacade.getAllVeiculo();
    }

    @PutMapping("/{id}")
    public void updateVeiculo(@RequestBody Veiculo veiculo) {
        veiculoFacade.updateVeiculo(veiculo);
    }

    @DeleteMapping("/{id}")
    public void deleteVeiculo(@PathVariable long id) {
        veiculoFacade.deleteVeiculo(id);
    }
}
