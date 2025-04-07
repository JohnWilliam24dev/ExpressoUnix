package com.johnwilliam.ExpressoUnix.Repositories;

import java.util.List;


import org.springframework.stereotype.Repository;

import com.johnwilliam.ExpressoUnix.Entities.Venda;
import com.johnwilliam.ExpressoUnix.Repositories.JPA.VendaJPA;

@Repository
public class VendaRepository {
    private final VendaJPA vendaJPA;

    public VendaRepository(VendaJPA vendaJPA) {
        this.vendaJPA = vendaJPA;
    }
    
    public void createVenda(Venda venda) {
        vendaJPA.save(venda);
    }
    
    public Venda getVendaById(long id) {
        return vendaJPA.findById(id).get();
    }
    
    public List<Venda> getAllVenda() {
        return vendaJPA.findAll();
    }
    
    public void updateVenda( Venda venda) {
        
        this.vendaJPA.save(venda);
    }
    
    public void deleteVenda(long id) {
        vendaJPA.deleteById(id);
    }
}
