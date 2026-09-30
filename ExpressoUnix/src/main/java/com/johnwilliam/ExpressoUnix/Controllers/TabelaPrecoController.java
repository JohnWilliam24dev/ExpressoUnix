package com.johnwilliam.ExpressoUnix.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.johnwilliam.ExpressoUnix.Applications.TabelaPrecoApplication;
import com.johnwilliam.ExpressoUnix.DTO.TabelaPrecoDTO;
import com.johnwilliam.ExpressoUnix.Enums.Classe;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tabela-preco")
public class TabelaPrecoController {

    private final TabelaPrecoApplication tabelaPrecoApplication;

    public TabelaPrecoController(TabelaPrecoApplication tabelaPrecoApplication) {
        this.tabelaPrecoApplication = tabelaPrecoApplication;
    }

    @GetMapping
    public List<TabelaPrecoDTO> getAll() {
        return tabelaPrecoApplication.getAll();
    }

    @PutMapping("/{classe}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMultiplicador(@PathVariable Classe classe, @Valid @RequestBody TabelaPrecoDTO tabela) {
        tabelaPrecoApplication.updateMultiplicador(classe, tabela);
    }
}
