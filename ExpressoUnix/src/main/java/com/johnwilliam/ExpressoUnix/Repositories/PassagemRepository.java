package com.johnwilliam.ExpressoUnix.Repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.johnwilliam.ExpressoUnix.Exceptions.ResourceNotFoundException;
import com.johnwilliam.ExpressoUnix.Models.PassagemModels;
import com.johnwilliam.ExpressoUnix.Repositories.JPA.PassagemJPA;

@Repository
public class PassagemRepository {
    private final PassagemJPA passagemJPA;

    public PassagemRepository(PassagemJPA passagemJPA) {
        this.passagemJPA = passagemJPA;
    }

    /** Devolve a passagem salva, ja com o ID gerado pelo banco. */
    public PassagemModels createPassagem(PassagemModels passagem) {
        return passagemJPA.save(passagem);
    }

    public PassagemModels getPassagemById(long id) {
        return passagemJPA.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Passagem", id));
    }

    public List<PassagemModels> getAllPassagem() {
        return passagemJPA.findAll();
    }

    public List<PassagemModels> getByVenda(long idVenda) {
        return passagemJPA.findByIdVendaOrderByIdAsc(idVenda);
    }
}
