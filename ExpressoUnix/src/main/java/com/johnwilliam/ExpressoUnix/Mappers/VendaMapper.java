package com.johnwilliam.ExpressoUnix.Mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.johnwilliam.ExpressoUnix.DTO.VendaDTO;
import com.johnwilliam.ExpressoUnix.Models.PassagemModels;
import com.johnwilliam.ExpressoUnix.Models.VendaModels;

/** Venda e criada por um fluxo proprio (VendaApplication); o mapper cobre a saida (Model -> DTO). */
@Component
public class VendaMapper {
    private final PassagemMapper passagemMapper;

    public VendaMapper(PassagemMapper passagemMapper) {
        this.passagemMapper = passagemMapper;
    }

    public VendaDTO modelToDTO(VendaModels model, List<PassagemModels> passagens) {
        VendaDTO dto = new VendaDTO();
        dto.setId(model.getId());
        dto.setHorarioEmissao(model.getHorarioEmissao());
        dto.setIdFuncionario(model.getIdFuncionario());
        dto.setStatus(model.getStatus());
        dto.setValorTotal(model.getValorTotal());
        dto.setDescontoTotal(model.getDescontoTotal());
        dto.setPassagens(passagemMapper.modelToDTOList(passagens));
        return dto;
    }
}
