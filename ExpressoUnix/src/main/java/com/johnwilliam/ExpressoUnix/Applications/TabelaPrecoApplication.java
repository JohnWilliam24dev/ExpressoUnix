package com.johnwilliam.ExpressoUnix.Applications;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.johnwilliam.ExpressoUnix.DTO.TabelaPrecoDTO;
import com.johnwilliam.ExpressoUnix.Enums.Classe;
import com.johnwilliam.ExpressoUnix.Models.TabelaPrecoModels;
import com.johnwilliam.ExpressoUnix.Repositories.TabelaPrecoRepository;

@Service
public class TabelaPrecoApplication {
    private final TabelaPrecoRepository tabelaPrecoRepository;

    public TabelaPrecoApplication(TabelaPrecoRepository tabelaPrecoRepository) {
        this.tabelaPrecoRepository = tabelaPrecoRepository;
    }

    public List<TabelaPrecoDTO> getAll() {
        return tabelaPrecoRepository.getAll().stream()
                .sorted(Comparator.comparing(TabelaPrecoModels::getClasse))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /** Atualiza o multiplicador da classe (cria a linha se ainda nao existir). */
    public void updateMultiplicador(Classe classe, TabelaPrecoDTO dto) {
        TabelaPrecoModels tabela = tabelaPrecoRepository.findByClasse(classe)
                .orElseGet(() -> new TabelaPrecoModels(classe, dto.getMultiplicador()));
        tabela.setMultiplicador(dto.getMultiplicador());
        tabelaPrecoRepository.save(tabela);
    }

    private TabelaPrecoDTO toDTO(TabelaPrecoModels model) {
        return new TabelaPrecoDTO(model.getClasse(), model.getMultiplicador());
    }
}
