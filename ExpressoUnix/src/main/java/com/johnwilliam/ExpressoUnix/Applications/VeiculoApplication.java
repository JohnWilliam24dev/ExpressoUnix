package com.johnwilliam.ExpressoUnix.Applications;

import java.util.List;
import org.springframework.stereotype.Service;
import com.johnwilliam.ExpressoUnix.Entities.Veiculo;
import com.johnwilliam.ExpressoUnix.Repositories.VeiculoRepository;

@Service
public class VeiculoApplication {
    private VeiculoRepository veiculoRepository;
    
    public VeiculoApplication(VeiculoRepository veiculoRepository){
        this.veiculoRepository = veiculoRepository;
    }
    
    public void createVeiculo(Veiculo veiculo){
        veiculoRepository.createVeiculo(veiculo);
    }
    
    public Veiculo getVeiculoById(long id) {
        return veiculoRepository.getVeiculoById(id);
    }
    
    public List<Veiculo> getAllVeiculo() {
        return veiculoRepository.getAllVeiculo();
    }
    
    public void updateVeiculo( Veiculo veiculo) {
        veiculoRepository.updateVeiculo(veiculo);
    }
    
    public void deleteVeiculo(long id) {
        veiculoRepository.deleteVeiculo(id);
    }
}
