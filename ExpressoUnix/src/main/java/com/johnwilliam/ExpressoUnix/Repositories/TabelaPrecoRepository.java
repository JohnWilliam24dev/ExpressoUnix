package com.johnwilliam.ExpressoUnix.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.johnwilliam.ExpressoUnix.Enums.Classe;
import com.johnwilliam.ExpressoUnix.Models.TabelaPrecoModels;
import com.johnwilliam.ExpressoUnix.Repositories.JPA.TabelaPrecoJPA;

@Repository
public class TabelaPrecoRepository {
    private final TabelaPrecoJPA tabelaPrecoJPA;

    public TabelaPrecoRepository(TabelaPrecoJPA tabelaPrecoJPA) {
        this.tabelaPrecoJPA = tabelaPrecoJPA;
    }

    public Optional<TabelaPrecoModels> findByClasse(Classe classe) {
        return tabelaPrecoJPA.findByClasse(classe);
    }

    public List<TabelaPrecoModels> getAll() {
        return tabelaPrecoJPA.findAll();
    }

    public long count() {
        return tabelaPrecoJPA.count();
    }

    public TabelaPrecoModels save(TabelaPrecoModels tabela) {
        return tabelaPrecoJPA.save(tabela);
    }
}
