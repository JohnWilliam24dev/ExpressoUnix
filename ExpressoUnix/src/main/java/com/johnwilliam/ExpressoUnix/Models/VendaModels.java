package com.johnwilliam.ExpressoUnix.Models;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity

public class VendaModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime horarioEmissao;

    @ManyToOne
    @JoinColumn(name = "id_funcionario", referencedColumnName = "id", insertable = false, updatable = false)
    private FuncionarioModels funcionario;
    @Column(name = "id_funcionario", nullable = false)
    private long idFuncionario;

    @ManyToOne
    @JoinColumn(name = "id_passagem", referencedColumnName = "id", insertable = false, updatable = false)
    private PassagemModels passagem;
    @Column(name = "id_passagem", nullable = false)
    private long idPassagem;

    public VendaModels() {}

    public VendaModels(FuncionarioModels funcionario, PassagemModels passagem) {
        this.funcionario = funcionario;
        this.idFuncionario = (funcionario != null) ? funcionario.getId() : 0;
        this.passagem = passagem;
        this.idPassagem = (passagem != null) ? passagem.getId() : 0;
    }

    public VendaModels(Long id, FuncionarioModels funcionario, PassagemModels passagem) {
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


    public PassagemModels getPassagem() { return passagem; }
    public void setPassagem(PassagemModels passagem) { this.passagem = passagem; }
}
