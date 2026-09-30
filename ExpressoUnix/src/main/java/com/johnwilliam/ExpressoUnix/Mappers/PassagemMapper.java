package com.johnwilliam.ExpressoUnix.Mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.johnwilliam.ExpressoUnix.DTO.PassagemDTO;
import com.johnwilliam.ExpressoUnix.Models.PassagemModels;

/** Passagem so e criada dentro de uma venda, entao o mapper cobre apenas a saida (Model -> DTO). */
@Component
public class PassagemMapper {

    public PassagemDTO modelToDTO(PassagemModels model) {
        PassagemDTO dto = new PassagemDTO();
        dto.setId(model.getId());
        dto.setIdVenda(model.getIdVenda());
        dto.setTipoTrecho(model.getTipoTrecho());
        dto.setIdPassagemVinculada(model.getIdPassagemVinculada());
        dto.setStatus(model.getStatus());
        dto.setIdViagem(model.getIdViagem());
        dto.setIdAssento(model.getIdAssento());
        dto.setIdPassageiro(model.getIdPassageiro());
        dto.setDataPassagem(model.getDataPassagem());
        dto.setHoraPassagem(model.getHoraPassagem());
        dto.setOrigem(model.getOrigem());
        dto.setDestino(model.getDestino());
        dto.setDistancia(model.getDistancia());
        dto.setTipoTarifa(model.getTipoTarifa());
        dto.setTarifaBase(model.getTarifaBase());
        dto.setDesconto(model.getDesconto());
        dto.setValorPago(model.getValorPago());
        return dto;
    }

    public List<PassagemDTO> modelToDTOList(List<PassagemModels> list) {
        return list.stream().map(this::modelToDTO).collect(Collectors.toList());
    }
}
