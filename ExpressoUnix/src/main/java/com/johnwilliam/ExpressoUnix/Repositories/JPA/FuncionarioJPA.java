package com.johnwilliam.ExpressoUnix.Repositories.JPA;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnwilliam.ExpressoUnix.Models.FuncionarioModels;

public interface FuncionarioJPA extends JpaRepository<FuncionarioModels,Long>{

}
