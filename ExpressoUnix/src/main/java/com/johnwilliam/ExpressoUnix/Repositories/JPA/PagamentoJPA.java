package com.johnwilliam.ExpressoUnix.Repositories.JPA;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnwilliam.ExpressoUnix.Models.PagamentoModels;

public interface PagamentoJPA extends JpaRepository<PagamentoModels, Long> {

    List<PagamentoModels> findByIdVendaOrderByIdAsc(long idVenda);
}
