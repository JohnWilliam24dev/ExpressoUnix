package com.johnwilliam.ExpressoUnix.Repositories.JPA;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnwilliam.ExpressoUnix.Enums.Classe;
import com.johnwilliam.ExpressoUnix.Models.TabelaPrecoModels;

public interface TabelaPrecoJPA extends JpaRepository<TabelaPrecoModels, Long> {

    Optional<TabelaPrecoModels> findByClasse(Classe classe);
}
