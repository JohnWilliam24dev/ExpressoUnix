package com.johnwilliam.ExpressoUnix.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.johnwilliam.ExpressoUnix.Applications.RotaApplication;
import com.johnwilliam.ExpressoUnix.DTO.RotaDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rota")
public class RotaController {

    private final RotaApplication rotaApplication;

    public RotaController(RotaApplication rotaApplication) {
        this.rotaApplication = rotaApplication;
    }

    /** Devolve a rota criada (com ID) para o cliente nao precisar consultar de novo. */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RotaDTO createRota(@Valid @RequestBody RotaDTO rota) {
        rota.setId(null);
        return rotaApplication.createRota(rota);
    }

    @GetMapping("/{id}")
    public RotaDTO getRotaById(@PathVariable long id) {
        return rotaApplication.getRotaById(id);
    }

    @GetMapping
    public List<RotaDTO> getAllRotas() {
        return rotaApplication.getAllRota();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRota(@PathVariable long id, @Valid @RequestBody RotaDTO rota) {
        rota.setId(id);
        rotaApplication.updateRota(rota);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRota(@PathVariable long id) {
        rotaApplication.deleteRota(id);
    }
}
