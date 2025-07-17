package com.johnwilliam.ExpressoUnix.Applications;

import java.util.List;
import org.springframework.stereotype.Service;

import com.johnwilliam.ExpressoUnix.Entities.Viagem;
import com.johnwilliam.ExpressoUnix.Entities.Mappers.ViagemMapper;
import com.johnwilliam.ExpressoUnix.Models.VeiculoModels;
import com.johnwilliam.ExpressoUnix.Models.ViagemModels;
import com.johnwilliam.ExpressoUnix.Repositories.AssentoRepository;
import com.johnwilliam.ExpressoUnix.Repositories.VeiculoRepository;
import com.johnwilliam.ExpressoUnix.Repositories.ViagemRepository;

@Service
public class ViagemApplication {
    private ViagemRepository viagemRepository;
    private VeiculoRepository veiculoRepository;
    private AssentoRepository assentoRepository;
    public ViagemApplication(ViagemRepository viagemRepository,VeiculoRepository veiculoRepository, AssentoRepository assentoRepository){
        this.viagemRepository = viagemRepository;
        this.veiculoRepository=veiculoRepository;
        this.assentoRepository=assentoRepository;
    }
    
    public void createViagem(ViagemModels viagemModel){
        
        ViagemModels viagemCriada=viagemRepository.createViagem(viagemModel);
        VeiculoModels veiculo=veiculoRepository.getVeiculoById(viagemCriada.getIdVeiculo());
        Viagem viagem= ViagemMapper.toEntity(viagemCriada,veiculo);
        assentoRepository.createAssento(viagem.disponibilizarAssentos());
        

        
    }
    
    public ViagemModels getViagemById(long id) {
        return viagemRepository.getViagemById(id);
    }
    
    public List<ViagemModels> getAllViagem() {
        return viagemRepository.getAllViagem();
    }
    
    public void updateViagem(ViagemModels viagem) {
        viagemRepository.updateViagem( viagem);
    }
    
    public void deleteViagem(long id) {
        viagemRepository.deleteViagem(id);
    }

    
}
