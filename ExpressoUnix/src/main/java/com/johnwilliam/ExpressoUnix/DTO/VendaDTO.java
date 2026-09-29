package com.johnwilliam.ExpressoUnix.DTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Positive;

public class VendaDTO {
    private Long id;

    /** Somente leitura: preenchido pelo banco na emissao. */
    private LocalDateTime horarioEmissao;

    @Positive(message = "idFuncionario deve ser informado")
    private long idFuncionario;

    @Positive(message = "idPassagem deve ser informado")
    private long idPassagem;

    public VendaDTO() {}

    public VendaDTO(long idFuncionario, long idPassagem) {
        this.idFuncionario = idFuncionario;
        this.idPassagem = idPassagem;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getHorarioEmissao() { return horarioEmissao; }
    public void setHorarioEmissao(LocalDateTime horarioEmissao) { this.horarioEmissao = horarioEmissao; }

    public long getIdFuncionario() { return idFuncionario; }
    public void setIdFuncionario(long idFuncionario) { this.idFuncionario = idFuncionario; }

    public long getIdPassagem() { return idPassagem; }
    public void setIdPassagem(long idPassagem) { this.idPassagem = idPassagem; }
}
