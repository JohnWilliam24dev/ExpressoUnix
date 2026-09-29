package com.johnwilliam.ExpressoUnix.Applications;

import java.util.List;
import org.springframework.stereotype.Service;

import com.johnwilliam.ExpressoUnix.DTO.VendaDTO;
import com.johnwilliam.ExpressoUnix.Entities.Venda;
import com.johnwilliam.ExpressoUnix.Mappers.VendaMapper;
import com.johnwilliam.ExpressoUnix.Repositories.FuncionarioRepository;
import com.johnwilliam.ExpressoUnix.Repositories.PassagemRepository;
import com.johnwilliam.ExpressoUnix.Repositories.VendaRepository;

@Service
public class VendaApplication {
    private VendaRepository vendaRepository;
    private VendaMapper vendaMapper;
    private FuncionarioRepository funcionarioRepository;
    private PassagemRepository passagemRepository;

    public VendaApplication(VendaRepository vendaRepository, VendaMapper vendaMapper,
                            FuncionarioRepository funcionarioRepository, PassagemRepository passagemRepository){
        this.vendaRepository = vendaRepository;
        this.vendaMapper= vendaMapper;
        this.funcionarioRepository = funcionarioRepository;
        this.passagemRepository = passagemRepository;
    }

    public void createVenda(VendaDTO venda){
        validarReferencias(venda);
        Venda entity= vendaMapper.DTOtoEntity(venda);
        vendaRepository.createVenda(vendaMapper.entityToModel(entity));
    }

    public VendaDTO getVendaById(long id) {
        return vendaMapper.modelToDTO(vendaRepository.getVendaById(id));
    }

    public List<VendaDTO> getAllVenda() {
        return vendaMapper.modelToDTOList(vendaRepository.getAllVenda());
    }

    public void updateVenda( VendaDTO venda) {
        validarReferencias(venda);
        Venda entity= vendaMapper.DTOtoEntity(venda);
        vendaRepository.updateVenda( vendaMapper.entityToModel(entity));
    }

    public void deleteVenda(long id) {
        vendaRepository.deleteVenda(id);
    }

    /** Lanca 404 se o funcionario ou a passagem informados nao existem. */
    private void validarReferencias(VendaDTO venda) {
        funcionarioRepository.getFuncionarioById(venda.getIdFuncionario());
        passagemRepository.getPassagemById(venda.getIdPassagem());
    }
}
