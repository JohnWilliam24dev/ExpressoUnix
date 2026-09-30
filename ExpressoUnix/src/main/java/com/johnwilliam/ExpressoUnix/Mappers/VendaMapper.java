package com.johnwilliam.ExpressoUnix.Mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.johnwilliam.ExpressoUnix.DTO.VendaDTO;
import com.johnwilliam.ExpressoUnix.Models.PagamentoModels;
import com.johnwilliam.ExpressoUnix.Models.PassagemModels;
import com.johnwilliam.ExpressoUnix.Models.VendaModels;

/** Venda e criada por um fluxo proprio (VendaApplication); o mapper cobre a saida (Model -> DTO). */
@Component
public class VendaMapper {
    private final PassagemMapper passagemMapper;
    private final PagamentoMapper pagamentoMapper;

    public VendaMapper(PassagemMapper passagemMapper, PagamentoMapper pagamentoMapper) {
        this.passagemMapper = passagemMapper;
        this.pagamentoMapper = pagamentoMapper;
    }

    public VendaDTO modelToDTO(VendaModels model, List<PassagemModels> passagens, List<PagamentoModels> pagamentos) {
        VendaDTO dto = new VendaDTO();
        dto.setId(model.getId());
        dto.setHorarioEmissao(model.getHorarioEmissao());
        dto.setIdFuncionario(model.getIdFuncionario());
        dto.setStatus(model.getStatus());
        dto.setValorTotal(model.getValorTotal());
        dto.setDescontoTotal(model.getDescontoTotal());
        dto.setPassagens(passagemMapper.modelToDTOList(passagens));
        dto.setPagamentos(pagamentoMapper.modelToDTOList(pagamentos));
        return dto;
    }
}
