package com.johnwilliam.ExpressoUnix.Mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.johnwilliam.ExpressoUnix.DTO.PagamentoDTO;
import com.johnwilliam.ExpressoUnix.Models.PagamentoModels;

@Component
public class PagamentoMapper {

    public PagamentoDTO modelToDTO(PagamentoModels model) {
        PagamentoDTO dto = new PagamentoDTO();
        dto.setId(model.getId());
        dto.setIdVenda(model.getIdVenda());
        dto.setForma(model.getForma());
        dto.setValor(model.getValor());
        dto.setValorRecebido(model.getValorRecebido());
        dto.setTroco(model.getTroco());
        dto.setStatus(model.getStatus());
        dto.setHorario(model.getHorario());
        return dto;
    }

    public List<PagamentoDTO> modelToDTOList(List<PagamentoModels> list) {
        return list.stream().map(this::modelToDTO).collect(Collectors.toList());
    }
}
