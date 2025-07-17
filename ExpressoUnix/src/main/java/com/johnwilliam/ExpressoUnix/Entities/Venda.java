package com.johnwilliam.ExpressoUnix.Entities;
import java.time.LocalDateTime;

public class Venda {
    
    private Long id;
    
    private LocalDateTime horarioEmissao;
    
    private long funcionario;
    

   
    private long passagem;
    

    

    public Venda(long id, long funcionario, long passagem, LocalDateTime horarioEmissao) {
        this.funcionario = funcionario;
        
        this.passagem = passagem;
        
    }

   

    public long getFuncionario() {
        return funcionario;
    }



    public void setFuncionario(long funcionario) {
        this.funcionario = funcionario;
    }



    public long getPassagem() {
        return passagem;
    }



    public void setPassagem(long passagem) {
        this.passagem = passagem;
    }



    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getHorarioEmissao() { return horarioEmissao; }
    public void setHorarioEmissao(LocalDateTime horarioEmissao) { this.horarioEmissao = horarioEmissao; }

}
