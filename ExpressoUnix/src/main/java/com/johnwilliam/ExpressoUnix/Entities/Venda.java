package com.johnwilliam.ExpressoUnix.Entities;
import java.time.LocalDateTime;

public class Venda {
    
    private Long id;
    
    private LocalDateTime horarioEmissao;
    
    private Funcionario funcionario;
    

   
    private Passagem passagem;
    

    

    public Venda(Funcionario funcionario, Passagem passagem) {
        this.funcionario = funcionario;
        
        this.passagem = passagem;
        
    }

   

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getHorarioEmissao() { return horarioEmissao; }
    public void setHorarioEmissao(LocalDateTime horarioEmissao) { this.horarioEmissao = horarioEmissao; }

}
