package com.johnwilliam.ExpressoUnix.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.johnwilliam.ExpressoUnix.Enums.StatusVenda;

/** Somente saida: a venda com suas passagens (cada uma com seu proprio status). */
public class VendaDTO {
    private Long id;
    private LocalDateTime horarioEmissao;
    private long idFuncionario;
    private StatusVenda status;
    private BigDecimal valorTotal;
    private BigDecimal descontoTotal;
    private List<PassagemDTO> passagens;
    private List<PagamentoDTO> pagamentos;

    public VendaDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getHorarioEmissao() { return horarioEmissao; }
    public void setHorarioEmissao(LocalDateTime horarioEmissao) { this.horarioEmissao = horarioEmissao; }

    public long getIdFuncionario() { return idFuncionario; }
    public void setIdFuncionario(long idFuncionario) { this.idFuncionario = idFuncionario; }

    public StatusVenda getStatus() { return status; }
    public void setStatus(StatusVenda status) { this.status = status; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    public BigDecimal getDescontoTotal() { return descontoTotal; }
    public void setDescontoTotal(BigDecimal descontoTotal) { this.descontoTotal = descontoTotal; }

    public List<PagamentoDTO> getPagamentos() { return pagamentos; }
    public void setPagamentos(List<PagamentoDTO> pagamentos) { this.pagamentos = pagamentos; }

    public List<PassagemDTO> getPassagens() { return passagens; }
    public void setPassagens(List<PassagemDTO> passagens) { this.passagens = passagens; }
}
