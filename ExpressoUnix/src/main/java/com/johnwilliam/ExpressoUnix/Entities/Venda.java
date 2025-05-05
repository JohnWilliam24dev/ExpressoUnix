package com.johnwilliam.ExpressoUnix.Entities;
import java.time.LocalDateTime;

public class Venda {
    
    private Long id;
    
    private LocalDateTime horarioEmissao;
    
    private Funcionario funcionario;
    
    private long idFuncionario;
   
    private Passagem passagem;
    
    private long idPassagem;

    public Venda() {}

    public Venda(Funcionario funcionario, Passagem passagem) {
        this.funcionario = funcionario;
        this.idFuncionario = (funcionario != null) ? funcionario.getId() : 0;
        this.passagem = passagem;
        this.idPassagem = (passagem != null) ? passagem.getId() : 0;
    }

    public Venda(Long id, Funcionario funcionario, Passagem passagem) {
        this.id = id;
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


    public Passagem getPassagem() { return passagem; }
    public void setPassagem(Passagem passagem) { this.passagem = passagem; }
}
