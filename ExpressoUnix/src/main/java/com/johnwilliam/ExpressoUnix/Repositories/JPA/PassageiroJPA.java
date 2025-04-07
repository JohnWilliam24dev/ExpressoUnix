package com.johnwilliam.ExpressoUnix.Repositories.JPA;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnwilliam.ExpressoUnix.Entities.Passageiro;

public interface PassageiroJPA extends JpaRepository<Passageiro,Long> {

}
