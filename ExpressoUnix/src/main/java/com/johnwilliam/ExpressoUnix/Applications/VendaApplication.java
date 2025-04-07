package com.johnwilliam.ExpressoUnix.Applications;

import java.util.List;
import org.springframework.stereotype.Service;
import com.johnwilliam.ExpressoUnix.Entities.Venda;
import com.johnwilliam.ExpressoUnix.Repositories.VendaRepository;

@Service
public class VendaApplication {
    private VendaRepository vendaRepository;
    
    public VendaApplication(VendaRepository vendaRepository){
        this.vendaRepository = vendaRepository;
    }
    
    public void createVenda(Venda venda){
        vendaRepository.createVenda(venda);
    }
    
    public Venda getVendaById(long id) {
        return vendaRepository.getVendaById(id);
    }
    
    public List<Venda> getAllVenda() {
        return vendaRepository.getAllVenda();
    }
    
    public void updateVenda( Venda venda) {
        vendaRepository.updateVenda( venda);
    }
    
    public void deleteVenda(long id) {
        vendaRepository.deleteVenda(id);
    }
}
