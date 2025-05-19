package com.johnwilliam.ExpressoUnix.Applications;

import java.util.List;

import org.springframework.stereotype.Service;


import com.johnwilliam.ExpressoUnix.Models.AssentoModels;
import com.johnwilliam.ExpressoUnix.Repositories.AssentoRepository;
@Service
public class AssentoApplication {
    private AssentoRepository assentoRepository;
    public AssentoApplication(AssentoRepository assentoRepository){
        this.assentoRepository=assentoRepository;
    }
    public void createAssento(AssentoModels assento){
        assentoRepository.createAssento(assento);
    }
    public void createAllAssento(List<AssentoModels> lista_AssentoModels){
        createAllAssento(lista_AssentoModels);
    }
    public AssentoModels getAssentoById(long id) {
        return assentoRepository.getAssentoById(id);
    }

    public List<AssentoModels> getAllAssento() {
        return assentoRepository.getAllAssento();
    }

    public void updateAssento( AssentoModels assento) {
        assentoRepository.updateAssento( assento);
    }

    public void deleteAssento(long id) {
        assentoRepository.deleteAssento(id);
    }
}
