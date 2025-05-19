package com.johnwilliam.ExpressoUnix.Applications;

import java.util.List;
import org.springframework.stereotype.Service;

import com.johnwilliam.ExpressoUnix.Entities.Veiculo;
import com.johnwilliam.ExpressoUnix.Entities.Viagem;

import com.johnwilliam.ExpressoUnix.Models.VeiculoModels;
import com.johnwilliam.ExpressoUnix.Models.ViagemModels;
import com.johnwilliam.ExpressoUnix.Repositories.ViagemRepository;

@Service
public class ViagemApplication {
    private ViagemRepository viagemRepository;
    private VeiculoApplication veiculoApplication;
    public ViagemApplication(ViagemRepository viagemRepository){
        this.viagemRepository = viagemRepository;
    }
    
    public void createViagem(ViagemModels viagemModel){
        Viagem viagem=transformModelToEntity(viagemModel);
        viagem.disponibilizarAssentos();
        viagemRepository.createViagem(viagemModel);
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

    public Viagem transformModelToEntity(ViagemModels viagemModels){
        Viagem viagem=new Viagem();
        long idVeiculo=viagemModels.getIdVeiculo();
        viagem.setDataViagem(viagemModels.getDataViagem());
        viagem.setHoraViagem(viagemModels.getHoraViagem());
        viagem.setOrigem(viagemModels.getOrigem());
        viagem.setDestino(viagemModels.getDestino());
        VeiculoModels veiculoModels= veiculoApplication.getVeiculoById(idVeiculo);
        Veiculo veiculo=veiculoApplication.transformModelToEntity(veiculoModels);
        viagem.setVeiculo(veiculo);
        return viagem;
    }
    public void transformEntityToModel(ViagemModels viagemModels, Viagem viagem){
        viagemModels.setDataViagem(viagem.getDataViagem());
        viagemModels.setHoraViagem(viagem.getHoraViagem());
        viagemModels.setOrigem(viagem.getOrigem().toString());
        viagemModels.setDestino(viagem.getDestino().toString());
    }
}
