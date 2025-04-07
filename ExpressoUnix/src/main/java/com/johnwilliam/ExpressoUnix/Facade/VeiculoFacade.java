package com.johnwilliam.ExpressoUnix.Facade;

import java.util.List;
import org.springframework.stereotype.Component;
import com.johnwilliam.ExpressoUnix.Applications.VeiculoApplication;
import com.johnwilliam.ExpressoUnix.Entities.Veiculo;

@Component
public class VeiculoFacade {
    private final VeiculoApplication veiculoApplication;
    
    public VeiculoFacade(VeiculoApplication veiculoApplication) {
        this.veiculoApplication = veiculoApplication;
    }
    
    public void createVeiculo(Veiculo veiculo) {
        veiculoApplication.createVeiculo(veiculo);
    }
    
    public Veiculo getVeiculoById(long id) {
        return veiculoApplication.getVeiculoById(id);
    }
    
    public List<Veiculo> getAllVeiculo() {
        return veiculoApplication.getAllVeiculo();
    }
    
    public void updateVeiculo(Veiculo veiculo) {
        veiculoApplication.updateVeiculo( veiculo);
    }
    
    public void deleteVeiculo(long id) {
        veiculoApplication.deleteVeiculo(id);
    }
}
