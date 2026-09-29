package com.johnwilliam.ExpressoUnix.Applications;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.johnwilliam.ExpressoUnix.DTO.AssentoDTO;
import com.johnwilliam.ExpressoUnix.DTO.PassagemDTO;
import com.johnwilliam.ExpressoUnix.Entities.Passagem;
import com.johnwilliam.ExpressoUnix.Enums.StatusAssento;
import com.johnwilliam.ExpressoUnix.Exceptions.BusinessException;
import com.johnwilliam.ExpressoUnix.Exceptions.ConflictException;
import com.johnwilliam.ExpressoUnix.Mappers.PassagemMapper;
import com.johnwilliam.ExpressoUnix.Models.PassagemModels;
import com.johnwilliam.ExpressoUnix.Repositories.PassagemRepository;

@Service
public class PassagemApplication {
    private PassagemRepository passagemRepository;
    private PassagemMapper passagemMapper;
    private AssentoApplication assentoApplication;

    public PassagemApplication(PassagemRepository passagemRepository, PassagemMapper passagemMapper,
                               AssentoApplication assentoApplication){
        this.passagemRepository = passagemRepository;
        this.passagemMapper= passagemMapper;
        this.assentoApplication = assentoApplication;
    }

    /**
     * Emite a passagem e marca o assento como Ocupado (na mesma transacao).
     * Viagem, assento e passageiro inexistentes resultam em 404 (via mapper).
     */
    @Transactional
    public void createPassagem(PassagemDTO passagem){
        Passagem entity = passagemMapper.DTOtoEntity(passagem);
        AssentoDTO assento = entity.getAssento();

        if (assento.getIdViagem() != passagem.getIdViagem()) {
            throw new BusinessException("O assento informado nao pertence a viagem informada");
        }
        if (assento.getStatusAssento() != StatusAssento.Livre) {
            throw new ConflictException("O assento " + assento.getNumeroAssento() + " ja esta ocupado");
        }

        passagemRepository.createPassagem(passagemMapper.entityToModel(entity));
        assentoApplication.alterarStatus(assento.getId(), StatusAssento.Ocupado);
    }

    public PassagemDTO getPassagemById(long id) {

        return passagemMapper.modelToDTO(passagemRepository.getPassagemById(id));
    }

    public List<PassagemDTO> getAllPassagem() {
        return passagemMapper.modelToDTOList(passagemRepository.getAllPassagem());
    }

    public void updatePassagem( PassagemDTO passagem) {
        PassagemModels atual = passagemRepository.getPassagemById(passagem.getId());
        if (atual.getIdViagem() != passagem.getIdViagem() || atual.getIdAssento() != passagem.getIdAssento()) {
            throw new BusinessException(
                "Nao e permitido alterar viagem ou assento de uma passagem existente; exclua e emita uma nova");
        }
        Passagem entity = passagemMapper.DTOtoEntity(passagem);
        passagemRepository.updatePassagem( passagemMapper.entityToModel(entity));
    }

    /** Exclui a passagem e libera o assento (na mesma transacao). */
    @Transactional
    public void deletePassagem(long id) {
        PassagemModels atual = passagemRepository.getPassagemById(id);
        passagemRepository.deletePassagem(id);
        assentoApplication.alterarStatus(atual.getIdAssento(), StatusAssento.Livre);
    }
}
