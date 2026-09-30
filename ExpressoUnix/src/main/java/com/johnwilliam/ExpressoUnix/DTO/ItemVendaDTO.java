package com.johnwilliam.ExpressoUnix.DTO;

import com.johnwilliam.ExpressoUnix.Enums.TipoTarifa;
import com.johnwilliam.ExpressoUnix.Enums.TipoTrecho;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

/** Uma passagem a ser emitida dentro da venda. Nao aceita preco: o servidor calcula. */
public class ItemVendaDTO {

    @NotNull(message = "tipoTrecho e obrigatorio (AVULSA, IDA ou VOLTA)")
    private TipoTrecho tipoTrecho;

    @Positive(message = "idViagem deve ser informado")
    private long idViagem;

    @Positive(message = "idAssento deve ser informado")
    private long idAssento;

    @Positive(message = "idPassageiro deve ser informado")
    private long idPassageiro;

    private TipoTarifa tipoTarifa = TipoTarifa.INTEIRA;

    /** So para VOLTA: indice (base 0) do item IDA correspondente na lista de itens da mesma venda. */
    @PositiveOrZero(message = "vinculadaAoItem deve ser um indice valido (0 ou mais)")
    private Integer vinculadaAoItem;

    public ItemVendaDTO() {}

    public TipoTrecho getTipoTrecho() { return tipoTrecho; }
    public void setTipoTrecho(TipoTrecho tipoTrecho) { this.tipoTrecho = tipoTrecho; }

    public long getIdViagem() { return idViagem; }
    public void setIdViagem(long idViagem) { this.idViagem = idViagem; }

    public long getIdAssento() { return idAssento; }
    public void setIdAssento(long idAssento) { this.idAssento = idAssento; }

    public long getIdPassageiro() { return idPassageiro; }
    public void setIdPassageiro(long idPassageiro) { this.idPassageiro = idPassageiro; }

    public TipoTarifa getTipoTarifa() { return tipoTarifa; }
    public void setTipoTarifa(TipoTarifa tipoTarifa) { this.tipoTarifa = tipoTarifa; }

    public Integer getVinculadaAoItem() { return vinculadaAoItem; }
    public void setVinculadaAoItem(Integer vinculadaAoItem) { this.vinculadaAoItem = vinculadaAoItem; }
}
