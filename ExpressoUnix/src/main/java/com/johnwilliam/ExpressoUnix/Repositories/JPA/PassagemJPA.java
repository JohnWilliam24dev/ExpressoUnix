package com.johnwilliam.ExpressoUnix.Repositories.JPA;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnwilliam.ExpressoUnix.Models.PassagemModels;

public interface PassagemJPA extends JpaRepository<PassagemModels,Long>{

    List<PassagemModels> findByIdVendaOrderByIdAsc(long idVenda);
}
