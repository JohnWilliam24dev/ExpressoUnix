package com.johnwilliam.ExpressoUnix.Repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.johnwilliam.ExpressoUnix.Exceptions.ResourceNotFoundException;
import com.johnwilliam.ExpressoUnix.Models.VendaModels;
import com.johnwilliam.ExpressoUnix.Repositories.JPA.VendaJPA;

@Repository
public class VendaRepository {
    private final VendaJPA vendaJPA;

    public VendaRepository(VendaJPA vendaJPA) {
        this.vendaJPA = vendaJPA;
    }

    /** Devolve a venda salva, ja com ID e horario de emissao. */
    public VendaModels createVenda(VendaModels venda) {
        return vendaJPA.save(venda);
    }

    public VendaModels getVendaById(long id) {
        return vendaJPA.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venda", id));
    }

    public List<VendaModels> getAllVenda() {
        return vendaJPA.findAll();
    }

    public void updateVenda(VendaModels venda) {
        if (venda.getId() == null || !vendaJPA.existsById(venda.getId())) {
            throw new ResourceNotFoundException("Venda", venda.getId());
        }
        vendaJPA.save(venda);
    }
}
