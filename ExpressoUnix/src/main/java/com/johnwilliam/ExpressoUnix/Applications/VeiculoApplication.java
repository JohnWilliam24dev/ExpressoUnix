package com.johnwilliam.ExpressoUnix.Applications;

import java.util.List;
import org.springframework.stereotype.Service;

import com.johnwilliam.ExpressoUnix.Entities.Veiculo;
import com.johnwilliam.ExpressoUnix.Mappers.VeiculoMapper;
import com.johnwilliam.ExpressoUnix.DTO.VeiculoDTO;
import com.johnwilliam.ExpressoUnix.Repositories.VeiculoRepository;

@Service
public class VeiculoApplication {
    private VeiculoRepository veiculoRepository;
    private VeiculoMapper veiculoMapper;
    
    public VeiculoApplication(VeiculoRepository veiculoRepository, VeiculoMapper veiculoMapper){
        this.veiculoRepository = veiculoRepository;
        this.veiculoMapper=veiculoMapper;
    }
    
    public void createVeiculo(VeiculoDTO veiculo){
        Veiculo entity= veiculoMapper.DTOtoEntity(veiculo);
        veiculoRepository.createVeiculo(veiculoMapper.entityToModel(entity));
    }
    
    public VeiculoDTO getVeiculoById(long id) {
        return veiculoMapper.modelToDTO(veiculoRepository.getVeiculoById(id)); 
    }
    
    public List<VeiculoDTO> getAllVeiculo() {
        return veiculoMapper.modelToDTOList(veiculoRepository.getAllVeiculo()); 
    }
    
    public void updateVeiculo( VeiculoDTO veiculo) {
        Veiculo entity= veiculoMapper.DTOtoEntity(veiculo);
        veiculoRepository.updateVeiculo(veiculoMapper.entityToModel(entity));
    }
    
    public void deleteVeiculo(long id) {
        veiculoRepository.deleteVeiculo(id);
    }
    
}
