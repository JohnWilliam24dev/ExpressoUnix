package com.johnwilliam.ExpressoUnix.Applications;

import java.util.List;
import org.springframework.stereotype.Service;

import com.johnwilliam.ExpressoUnix.DTO.VendaDTO;
import com.johnwilliam.ExpressoUnix.Entities.Venda;
import com.johnwilliam.ExpressoUnix.Mappers.VendaMapper;
import com.johnwilliam.ExpressoUnix.Repositories.VendaRepository;

@Service
public class VendaApplication {
    private VendaRepository vendaRepository;
    private VendaMapper vendaMapper;
    
    public VendaApplication(VendaRepository vendaRepository, VendaMapper vendaMapper){
        this.vendaRepository = vendaRepository;
        this.vendaMapper= vendaMapper;
    }
    
    public void createVenda(VendaDTO venda){
        Venda entity= vendaMapper.DTOtoEntity(venda);
        vendaRepository.createVenda(vendaMapper.entityToModel(entity));
    }
    
    public VendaDTO getVendaById(long id) {
        return vendaMapper.modelToDTO(vendaRepository.getVendaById(id)); 
    }
    
    public List<VendaDTO> getAllVenda() {
        return vendaMapper.modelToDTOList(vendaRepository.getAllVenda()); 
    }
    
    public void updateVenda( VendaDTO venda) {
        Venda entity= vendaMapper.DTOtoEntity(venda);
        vendaRepository.updateVenda( vendaMapper.entityToModel(entity));
    }
    
    public void deleteVenda(long id) {
        vendaRepository.deleteVenda(id);
    }
}
