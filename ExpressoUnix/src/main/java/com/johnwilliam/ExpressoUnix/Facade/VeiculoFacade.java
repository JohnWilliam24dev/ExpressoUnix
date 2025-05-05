package com.johnwilliam.ExpressoUnix.Facade;

import java.util.List;
import org.springframework.stereotype.Component;
import com.johnwilliam.ExpressoUnix.Applications.VeiculoApplication;

import com.johnwilliam.ExpressoUnix.Models.VeiculoModels;

@Component
public class VeiculoFacade {
    private final VeiculoApplication veiculoApplication;
    
    public VeiculoFacade(VeiculoApplication veiculoApplication) {
        this.veiculoApplication = veiculoApplication;
    }
    
    public void createVeiculo(VeiculoModels veiculo) {
        veiculoApplication.createVeiculo(veiculo);
    }
    
    public VeiculoModels getVeiculoById(long id) {
        return veiculoApplication.getVeiculoById(id);
    }
    
    public List<VeiculoModels> getAllVeiculo() {
        return veiculoApplication.getAllVeiculo();
    }
    
    public void updateVeiculo(VeiculoModels veiculo) {
        veiculoApplication.updateVeiculo( veiculo);
    }
    
    public void deleteVeiculo(long id) {
        veiculoApplication.deleteVeiculo(id);
    }
}
