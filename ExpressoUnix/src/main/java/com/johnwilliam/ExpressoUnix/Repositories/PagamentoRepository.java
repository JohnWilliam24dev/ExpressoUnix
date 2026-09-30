package com.johnwilliam.ExpressoUnix.Repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.johnwilliam.ExpressoUnix.Models.PagamentoModels;
import com.johnwilliam.ExpressoUnix.Repositories.JPA.PagamentoJPA;

@Repository
public class PagamentoRepository {
    private final PagamentoJPA pagamentoJPA;

    public PagamentoRepository(PagamentoJPA pagamentoJPA) {
        this.pagamentoJPA = pagamentoJPA;
    }

    public PagamentoModels createPagamento(PagamentoModels pagamento) {
        return pagamentoJPA.save(pagamento);
    }

    public List<PagamentoModels> getByVenda(long idVenda) {
        return pagamentoJPA.findByIdVendaOrderByIdAsc(idVenda);
    }
}
