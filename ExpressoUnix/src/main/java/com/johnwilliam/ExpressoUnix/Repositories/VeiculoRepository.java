package com.johnwilliam.ExpressoUnix.Repositories;

import java.util.List;


import org.springframework.stereotype.Repository;

import com.johnwilliam.ExpressoUnix.Exceptions.ResourceNotFoundException;
import com.johnwilliam.ExpressoUnix.Models.VeiculoModels;
import com.johnwilliam.ExpressoUnix.Repositories.JPA.VeiculoJPA;

@Repository
public class VeiculoRepository {
    private final VeiculoJPA veiculoJPA;

    public VeiculoRepository(VeiculoJPA veiculoJPA) {
        this.veiculoJPA = veiculoJPA;
    }
    
    public void createVeiculo(VeiculoModels veiculo) {
        veiculoJPA.save(veiculo);
    }
    
    public VeiculoModels getVeiculoById(long id) {
        return veiculoJPA.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veiculo", id));
    }
    
    public List<VeiculoModels> getAllVeiculo() {
        return veiculoJPA.findAll();
    }
    
    public void updateVeiculo(VeiculoModels veiculo) {
        if (veiculo.getId() == null || !veiculoJPA.existsById(veiculo.getId())) {
            throw new ResourceNotFoundException("Veiculo", veiculo.getId());
        }
        veiculoJPA.save(veiculo);
    }
    
    public void deleteVeiculo(long id) {
        veiculoJPA.deleteById(id);
    }
}
