package com.johnwilliam.ExpressoUnix.Applications;

import java.util.List;

import org.springframework.stereotype.Service;

import com.johnwilliam.ExpressoUnix.Entities.Assento;
import com.johnwilliam.ExpressoUnix.Repositories.AssentoRepository;
@Service
public class AssentoApplication {
    private AssentoRepository assentoRepository;
    public AssentoApplication(AssentoRepository assentoRepository){
        this.assentoRepository=assentoRepository;
    }
    public void createAssento(Assento assento){
        assentoRepository.createAssento(assento);
    }
    public Assento getAssentoById(long id) {
        return assentoRepository.getAssentoById(id);
    }

    public List<Assento> getAllAssento() {
        return assentoRepository.getAllAssento();
    }

    public void updateAssento( Assento assento) {
        assentoRepository.updateAssento( assento);
    }

    public void deleteAssento(long id) {
        assentoRepository.deleteAssento(id);
    }
}
