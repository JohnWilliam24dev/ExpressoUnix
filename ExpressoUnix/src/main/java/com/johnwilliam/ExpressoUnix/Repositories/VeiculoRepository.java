package com.johnwilliam.ExpressoUnix.Repositories;

import java.util.List;


import org.springframework.stereotype.Repository;

import com.johnwilliam.ExpressoUnix.Entities.Veiculo;
import com.johnwilliam.ExpressoUnix.Repositories.JPA.VeiculoJPA;

@Repository
public class VeiculoRepository {
    private final VeiculoJPA veiculoJPA;

    public VeiculoRepository(VeiculoJPA veiculoJPA) {
        this.veiculoJPA = veiculoJPA;
    }
    
    public void createVeiculo(Veiculo veiculo) {
        veiculoJPA.save(veiculo);
    }
    
    public Veiculo getVeiculoById(long id) {
        return veiculoJPA.findById(id).get();
    }
    
    public List<Veiculo> getAllVeiculo() {
        return veiculoJPA.findAll();
    }
    
    public void updateVeiculo( Veiculo veiculo) {
        
        this.veiculoJPA.save(veiculo);
    }
    
    public void deleteVeiculo(long id) {
        veiculoJPA.deleteById(id);
    }
}
