package com.johnwilliam.ExpressoUnix.Repositories.JPA;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnwilliam.ExpressoUnix.Models.PassageiroModels;

public interface PassageiroJPA extends JpaRepository<PassageiroModels,Long> {

}
