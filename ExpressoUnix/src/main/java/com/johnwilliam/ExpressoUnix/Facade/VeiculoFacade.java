package com.johnwilliam.ExpressoUnix.Facade;

import java.util.List;
import org.springframework.stereotype.Component;
import com.johnwilliam.ExpressoUnix.Applications.VeiculoApplication;

import com.johnwilliam.ExpressoUnix.DTO.VeiculoDTO;

@Component
public class VeiculoFacade {
    private final VeiculoApplication veiculoApplication;
    
    public VeiculoFacade(VeiculoApplication veiculoApplication) {
        this.veiculoApplication = veiculoApplication;
    }
    
    public void createVeiculo(VeiculoDTO veiculo) {
        veiculoApplication.createVeiculo(veiculo);
    }
    
    public VeiculoDTO getVeiculoById(long id) {
        return veiculoApplication.getVeiculoById(id);
    }
    
    public List<VeiculoDTO> getAllVeiculo() {
        return veiculoApplication.getAllVeiculo();
    }
    
    public void updateVeiculo(VeiculoDTO veiculo) {
        veiculoApplication.updateVeiculo( veiculo);
    }
    
    public void deleteVeiculo(long id) {
        veiculoApplication.deleteVeiculo(id);
    }
}
