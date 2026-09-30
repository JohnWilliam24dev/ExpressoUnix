package com.johnwilliam.ExpressoUnix.Repositories.JPA;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnwilliam.ExpressoUnix.Models.RotaModels;

public interface RotaJPA extends JpaRepository<RotaModels, Long> {

    Optional<RotaModels> findByOrigemIgnoreCaseAndDestinoIgnoreCase(String origem, String destino);
}
