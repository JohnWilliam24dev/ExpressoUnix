package com.johnwilliam.ExpressoUnix.Controllers;

import org.springframework.web.bind.annotation.*;

import com.johnwilliam.ExpressoUnix.Applications.PrecoApplication;
import com.johnwilliam.ExpressoUnix.DTO.CotacaoDTO;
import com.johnwilliam.ExpressoUnix.Enums.TipoTarifa;

@RestController
@RequestMapping("/preco")
public class PrecoController {

    private final PrecoApplication precoApplication;

    public PrecoController(PrecoApplication precoApplication) {
        this.precoApplication = precoApplication;
    }

    @GetMapping("/cotacao")
    public CotacaoDTO cotar(@RequestParam long idViagem,
                            @RequestParam(defaultValue = "INTEIRA") TipoTarifa tipoTarifa) {
        return precoApplication.cotar(idViagem, tipoTarifa);
    }
}
