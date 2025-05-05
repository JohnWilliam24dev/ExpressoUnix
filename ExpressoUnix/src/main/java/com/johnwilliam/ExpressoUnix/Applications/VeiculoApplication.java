package com.johnwilliam.ExpressoUnix.Applications;

import java.util.List;
import org.springframework.stereotype.Service;

import com.johnwilliam.ExpressoUnix.Models.VeiculoModels;
import com.johnwilliam.ExpressoUnix.Repositories.VeiculoRepository;

@Service
public class VeiculoApplication {
    private VeiculoRepository veiculoRepository;
    
    public VeiculoApplication(VeiculoRepository veiculoRepository){
        this.veiculoRepository = veiculoRepository;
    }
    
    public void createVeiculo(VeiculoModels veiculo){
        veiculoRepository.createVeiculo(veiculo);
    }
    
    public VeiculoModels getVeiculoById(long id) {
        return veiculoRepository.getVeiculoById(id);
    }
    
    public List<VeiculoModels> getAllVeiculo() {
        return veiculoRepository.getAllVeiculo();
    }
    
    public void updateVeiculo( VeiculoModels veiculo) {
        veiculoRepository.updateVeiculo(veiculo);
    }
    
    public void deleteVeiculo(long id) {
        veiculoRepository.deleteVeiculo(id);
    }
}
