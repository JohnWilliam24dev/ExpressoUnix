package com.johnwilliam.ExpressoUnix.Repositories.JPA;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnwilliam.ExpressoUnix.Entities.Funcionario;

public interface FuncionarioJPA extends JpaRepository<Funcionario,Long>{

}
