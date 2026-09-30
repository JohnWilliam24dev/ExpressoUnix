package com.johnwilliam.ExpressoUnix.Applications;

import java.util.List;

import org.springframework.stereotype.Service;

import com.johnwilliam.ExpressoUnix.DTO.PassagemDTO;
import com.johnwilliam.ExpressoUnix.Mappers.PassagemMapper;
import com.johnwilliam.ExpressoUnix.Repositories.PassagemRepository;

/**
 * Consulta de passagens. A emissao acontece em VendaApplication (uma passagem sempre pertence a uma venda);
 * cancelamento e remarcacao entram nos proximos blocos.
 */
@Service
public class PassagemApplication {
    private final PassagemRepository passagemRepository;
    private final PassagemMapper passagemMapper;

    public PassagemApplication(PassagemRepository passagemRepository, PassagemMapper passagemMapper) {
        this.passagemRepository = passagemRepository;
        this.passagemMapper = passagemMapper;
    }

    public PassagemDTO getPassagemById(long id) {
        return passagemMapper.modelToDTO(passagemRepository.getPassagemById(id));
    }

    public List<PassagemDTO> getAllPassagem() {
        return passagemMapper.modelToDTOList(passagemRepository.getAllPassagem());
    }
}
