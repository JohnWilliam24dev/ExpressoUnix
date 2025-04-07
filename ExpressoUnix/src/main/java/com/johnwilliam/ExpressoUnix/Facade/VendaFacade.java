package com.johnwilliam.ExpressoUnix.Facade;

import java.util.List;
import org.springframework.stereotype.Component;
import com.johnwilliam.ExpressoUnix.Applications.VendaApplication;
import com.johnwilliam.ExpressoUnix.Entities.Venda;

@Component
public class VendaFacade {
    private final VendaApplication vendaApplication;
    
    public VendaFacade(VendaApplication vendaApplication) {
        this.vendaApplication = vendaApplication;
    }
    
    public void createVenda(Venda venda) {
        vendaApplication.createVenda(venda);
    }
    
    public Venda getVendaById(long id) {
        return vendaApplication.getVendaById(id);
    }
    
    public List<Venda> getAllVenda() {
        return vendaApplication.getAllVenda();
    }
    
    public void updateVenda(Venda venda) {
        vendaApplication.updateVenda( venda);
    }
    
    public void deleteVenda(long id) {
        vendaApplication.deleteVenda(id);
    }
}
