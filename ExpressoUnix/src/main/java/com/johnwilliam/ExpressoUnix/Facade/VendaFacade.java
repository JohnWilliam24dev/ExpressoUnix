package com.johnwilliam.ExpressoUnix.Facade;

import java.util.List;
import org.springframework.stereotype.Component;
import com.johnwilliam.ExpressoUnix.Applications.VendaApplication;

import com.johnwilliam.ExpressoUnix.Models.VendaModels;

@Component
public class VendaFacade {
    private final VendaApplication vendaApplication;
    
    public VendaFacade(VendaApplication vendaApplication) {
        this.vendaApplication = vendaApplication;
    }
    
    public void createVenda(VendaModels venda) {
        vendaApplication.createVenda(venda);
    }
    
    public VendaModels getVendaById(long id) {
        return vendaApplication.getVendaById(id);
    }
    
    public List<VendaModels> getAllVenda() {
        return vendaApplication.getAllVenda();
    }
    
    public void updateVenda(VendaModels venda) {
        vendaApplication.updateVenda( venda);
    }
    
    public void deleteVenda(long id) {
        vendaApplication.deleteVenda(id);
    }
}
