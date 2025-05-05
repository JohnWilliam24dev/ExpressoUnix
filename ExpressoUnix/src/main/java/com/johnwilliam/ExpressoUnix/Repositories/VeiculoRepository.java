package com.johnwilliam.ExpressoUnix.Repositories;

import java.util.List;


import org.springframework.stereotype.Repository;

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
        return veiculoJPA.findById(id).get();
    }
    
    public List<VeiculoModels> getAllVeiculo() {
        return veiculoJPA.findAll();
    }
    
    public void updateVeiculo( VeiculoModels veiculo) {
        
        this.veiculoJPA.save(veiculo);
    }
    
    public void deleteVeiculo(long id) {
        veiculoJPA.deleteById(id);
    }
}
