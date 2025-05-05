package com.johnwilliam.ExpressoUnix.Repositories;

import java.util.List;


import org.springframework.stereotype.Repository;

import com.johnwilliam.ExpressoUnix.Models.VendaModels;
import com.johnwilliam.ExpressoUnix.Repositories.JPA.VendaJPA;

@Repository
public class VendaRepository {
    private final VendaJPA vendaJPA;

    public VendaRepository(VendaJPA vendaJPA) {
        this.vendaJPA = vendaJPA;
    }
    
    public void createVenda(VendaModels venda) {
        vendaJPA.save(venda);
    }
    
    public VendaModels getVendaById(long id) {
        return vendaJPA.findById(id).get();
    }
    
    public List<VendaModels> getAllVenda() {
        return vendaJPA.findAll();
    }
    
    public void updateVenda( VendaModels venda) {
        
        this.vendaJPA.save(venda);
    }
    
    public void deleteVenda(long id) {
        vendaJPA.deleteById(id);
    }
}
