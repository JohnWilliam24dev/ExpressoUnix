package com.johnwilliam.ExpressoUnix.Config;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.johnwilliam.ExpressoUnix.Enums.Classe;
import com.johnwilliam.ExpressoUnix.Models.TabelaPrecoModels;
import com.johnwilliam.ExpressoUnix.Repositories.TabelaPrecoRepository;

/**
 * Popula a tabela de precos com multiplicadores padrao quando ela esta vazia.
 * Os valores podem ser ajustados depois via PUT /tabela-preco/{classe}.
 */
@Component
public class TabelaPrecoSeeder implements ApplicationRunner {

    private static final Map<Classe, String> PADRAO = Map.of(
            Classe.Convencional, "1.00",
            Classe.Executivo, "1.25",
            Classe.SemiLeito, "1.50",
            Classe.Leito, "1.80",
            Classe.Premium, "2.20");

    private final TabelaPrecoRepository tabelaPrecoRepository;

    public TabelaPrecoSeeder(TabelaPrecoRepository tabelaPrecoRepository) {
        this.tabelaPrecoRepository = tabelaPrecoRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (tabelaPrecoRepository.count() > 0) {
            return;
        }
        PADRAO.forEach((classe, mult) ->
                tabelaPrecoRepository.save(new TabelaPrecoModels(classe, new BigDecimal(mult))));
    }
}
