package com.johnwilliam.ExpressoUnix.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.johnwilliam.ExpressoUnix.DTO.VeiculoDTO;
import com.johnwilliam.ExpressoUnix.Facade.VeiculoFacade;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    private final VeiculoFacade veiculoFacade;

    @Autowired
    public VeiculoController(VeiculoFacade veiculoFacade) {
        this.veiculoFacade = veiculoFacade;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createVeiculo(@Valid @RequestBody VeiculoDTO veiculo) {
        veiculo.setId(null); // criacao sempre gera um novo ID
        veiculoFacade.createVeiculo(veiculo);
    }

    @GetMapping("/{id}")
    public VeiculoDTO getVeiculoById(@PathVariable long id) {
        return veiculoFacade.getVeiculoById(id);
    }

    @GetMapping
    public List<VeiculoDTO> getAllVeiculos() {
        return veiculoFacade.getAllVeiculo();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateVeiculo(@PathVariable long id, @Valid @RequestBody VeiculoDTO veiculo) {
        veiculo.setId(id); // o ID da URL e a fonte de verdade
        veiculoFacade.updateVeiculo(veiculo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVeiculo(@PathVariable long id) {
        veiculoFacade.deleteVeiculo(id);
    }
}
