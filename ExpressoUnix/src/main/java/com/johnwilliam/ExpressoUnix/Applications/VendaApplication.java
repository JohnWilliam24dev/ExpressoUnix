package com.johnwilliam.ExpressoUnix.Applications;

import java.util.List;
import org.springframework.stereotype.Service;

import com.johnwilliam.ExpressoUnix.Models.VendaModels;
import com.johnwilliam.ExpressoUnix.Repositories.VendaRepository;

@Service
public class VendaApplication {
    private VendaRepository vendaRepository;
    
    public VendaApplication(VendaRepository vendaRepository){
        this.vendaRepository = vendaRepository;
    }
    
    public void createVenda(VendaModels venda){
        vendaRepository.createVenda(venda);
    }
    
    public VendaModels getVendaById(long id) {
        return vendaRepository.getVendaById(id);
    }
    
    public List<VendaModels> getAllVenda() {
        return vendaRepository.getAllVenda();
    }
    
    public void updateVenda( VendaModels venda) {
        vendaRepository.updateVenda( venda);
    }
    
    public void deleteVenda(long id) {
        vendaRepository.deleteVenda(id);
    }
}
