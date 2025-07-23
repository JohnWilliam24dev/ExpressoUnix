package com.johnwilliam.ExpressoUnix.DTO;

import java.time.LocalDateTime;

public class VendaDTO {
    private Long id;
    private LocalDateTime horarioEmissao;
    private long idFuncionario;
    private long idPassagem;
    private FuncionarioDTO funcionario;
    private PassagemDTO passagem;

    public VendaDTO() {}

    public VendaDTO(FuncionarioDTO funcionario, PassagemDTO passagem) {
        this.funcionario = funcionario;
        this.idFuncionario = (funcionario != null) ? funcionario.getId() : 0;
        this.passagem = passagem;
        this.idPassagem = (passagem != null) ? passagem.getId() : 0;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getHorarioEmissao() { return horarioEmissao; }
    public void setHorarioEmissao(LocalDateTime horarioEmissao) { this.horarioEmissao = horarioEmissao; }

    public long getIdFuncionario() { return idFuncionario; }
    public void setIdFuncionario(long idFuncionario) { this.idFuncionario = idFuncionario; }

    public long getIdPassagem() { return idPassagem; }
    public void setIdPassagem(long idPassagem) { this.idPassagem = idPassagem; }

    public FuncionarioDTO getFuncionario() { return funcionario; }
    public void setFuncionario(FuncionarioDTO funcionario) { this.funcionario = funcionario; }

    public PassagemDTO getPassagem() { return passagem; }
    public void setPassagem(PassagemDTO passagem) { this.passagem = passagem; }
}
