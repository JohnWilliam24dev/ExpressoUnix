package com.johnwilliam.ExpressoUnix.Applications;

import java.util.List;

import org.springframework.stereotype.Service;

import com.johnwilliam.ExpressoUnix.DTO.RotaDTO;
import com.johnwilliam.ExpressoUnix.Exceptions.BusinessException;
import com.johnwilliam.ExpressoUnix.Exceptions.ConflictException;
import com.johnwilliam.ExpressoUnix.Mappers.RotaMapper;
import com.johnwilliam.ExpressoUnix.Models.RotaModels;
import com.johnwilliam.ExpressoUnix.Repositories.RotaRepository;

@Service
public class RotaApplication {
    private final RotaRepository rotaRepository;
    private final RotaMapper rotaMapper;

    public RotaApplication(RotaRepository rotaRepository, RotaMapper rotaMapper) {
        this.rotaRepository = rotaRepository;
        this.rotaMapper = rotaMapper;
    }

    public RotaDTO createRota(RotaDTO rota) {
        validar(rota);
        garantirRotaUnica(rota, null);
        return rotaMapper.modelToDTO(rotaRepository.createRota(rotaMapper.DTOtoModel(rota)));
    }

    public RotaDTO getRotaById(long id) {
        return rotaMapper.modelToDTO(rotaRepository.getRotaById(id));
    }

    public List<RotaDTO> getAllRota() {
        return rotaMapper.modelToDTOList(rotaRepository.getAllRota());
    }

    public void updateRota(RotaDTO rota) {
        validar(rota);
        garantirRotaUnica(rota, rota.getId());
        rotaRepository.updateRota(rotaMapper.DTOtoModel(rota));
    }

    public void deleteRota(long id) {
        rotaRepository.deleteRota(id);
    }

    private void validar(RotaDTO rota) {
        if (rota.getOrigem().trim().equalsIgnoreCase(rota.getDestino().trim())) {
            throw new BusinessException("Origem e destino da rota nao podem ser iguais");
        }
    }

    /** Evita duplicidade sem depender apenas da constraint do banco (que e case-insensitive so no MySQL). */
    private void garantirRotaUnica(RotaDTO rota, Long idAtual) {
        rotaRepository.findByOrigemDestino(rota.getOrigem().trim(), rota.getDestino().trim())
                .filter(existente -> !existente.getId().equals(idAtual))
                .ifPresent(existente -> {
                    throw new ConflictException("Ja existe uma rota de " + existente.getOrigem()
                            + " para " + existente.getDestino());
                });
    }
}
