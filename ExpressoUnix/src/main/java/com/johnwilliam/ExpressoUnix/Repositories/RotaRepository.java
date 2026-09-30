package com.johnwilliam.ExpressoUnix.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.johnwilliam.ExpressoUnix.Exceptions.ResourceNotFoundException;
import com.johnwilliam.ExpressoUnix.Models.RotaModels;
import com.johnwilliam.ExpressoUnix.Repositories.JPA.RotaJPA;

@Repository
public class RotaRepository {
    private final RotaJPA rotaJPA;

    public RotaRepository(RotaJPA rotaJPA) {
        this.rotaJPA = rotaJPA;
    }

    public RotaModels createRota(RotaModels rota) {
        return rotaJPA.save(rota);
    }

    public RotaModels getRotaById(long id) {
        return rotaJPA.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rota", id));
    }

    public Optional<RotaModels> findByOrigemDestino(String origem, String destino) {
        return rotaJPA.findByOrigemIgnoreCaseAndDestinoIgnoreCase(origem, destino);
    }

    public List<RotaModels> getAllRota() {
        return rotaJPA.findAll();
    }

    public void updateRota(RotaModels rota) {
        if (rota.getId() == null || !rotaJPA.existsById(rota.getId())) {
            throw new ResourceNotFoundException("Rota", rota.getId());
        }
        rotaJPA.save(rota);
    }

    public void deleteRota(long id) {
        if (!rotaJPA.existsById(id)) {
            throw new ResourceNotFoundException("Rota", id);
        }
        rotaJPA.deleteById(id);
    }
}
