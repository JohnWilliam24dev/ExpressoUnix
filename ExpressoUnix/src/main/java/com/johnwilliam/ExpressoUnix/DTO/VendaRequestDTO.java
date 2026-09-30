package com.johnwilliam.ExpressoUnix.DTO;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

/** Entrada de POST /venda: usa apenas IDs; a resposta (VendaDTO) traz venda e passagens calculadas. */
public class VendaRequestDTO {

    // Temporario: sai do body quando a autenticacao entrar (VEN-08, bloco Seguranca).
    @Positive(message = "idFuncionario deve ser informado")
    private long idFuncionario;

    @NotEmpty(message = "a venda deve ter ao menos um item")
    @Valid
    private List<ItemVendaDTO> itens;

    @NotEmpty(message = "a venda deve ter ao menos um pagamento")
    @Valid
    private List<PagamentoRequestDTO> pagamentos;

    public VendaRequestDTO() {}

    public long getIdFuncionario() { return idFuncionario; }
    public void setIdFuncionario(long idFuncionario) { this.idFuncionario = idFuncionario; }

    public List<PagamentoRequestDTO> getPagamentos() { return pagamentos; }
    public void setPagamentos(List<PagamentoRequestDTO> pagamentos) { this.pagamentos = pagamentos; }

    public List<ItemVendaDTO> getItens() { return itens; }
    public void setItens(List<ItemVendaDTO> itens) { this.itens = itens; }
}
