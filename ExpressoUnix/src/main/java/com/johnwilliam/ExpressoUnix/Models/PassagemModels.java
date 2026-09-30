package com.johnwilliam.ExpressoUnix.Models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.johnwilliam.ExpressoUnix.Enums.StatusPassagem;
import com.johnwilliam.ExpressoUnix.Enums.TipoTarifa;
import com.johnwilliam.ExpressoUnix.Enums.TipoTrecho;

import jakarta.persistence.*;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"id_viagem", "id_assento"})})
public class PassagemModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusPassagem status;

    @ManyToOne
    @JoinColumn(name = "id_venda", referencedColumnName = "id", insertable = false, updatable = false)
    private VendaModels venda;
    @Column(name = "id_venda", nullable = false)
    private long idVenda;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private TipoTrecho tipoTrecho = TipoTrecho.AVULSA;

    /** Vinculo informativo: na VOLTA aponta para a IDA. Nunca comanda o comportamento das passagens. */
    @ManyToOne
    @JoinColumn(name = "id_passagem_vinculada", referencedColumnName = "id", insertable = false, updatable = false)
    private PassagemModels passagemVinculada;
    @Column(name = "id_passagem_vinculada")
    private Long idPassagemVinculada;

    @ManyToOne
    @JoinColumn(name = "id_viagem", referencedColumnName = "id", insertable = false, updatable = false)
    private ViagemModels viagem;
    @Column(name = "id_viagem", nullable = false)
    private long idViagem;

    @ManyToOne
    @JoinColumn(name = "id_assento", referencedColumnName = "id", insertable = false, updatable = false)
    private AssentoModels assento;
    @Column(name = "id_assento", nullable = false)
    private long idAssento;

    @ManyToOne
    @JoinColumn(name = "id_passageiro", referencedColumnName = "id", insertable = false, updatable = false)
    private PassageiroModels passageiro;
    @Column(name = "id_passageiro", nullable = false)
    private long idPassageiro;

    // origem, destino, data e hora repetem dados da Viagem (decisao em aberto 2 do pdv-features.md);
    // hoje sao preenchidos pelo servidor a partir da viagem.
    @Column(nullable = false)
    private LocalDate dataPassagem;

    @Column(nullable = false)
    private LocalTime horaPassagem;

    @Column(nullable = false, length = 100)
    private String origem;

    @Column(nullable = false, length = 100)
    private String destino;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal distancia;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoTarifa tipoTarifa = TipoTarifa.INTEIRA;

    /** Tarifa cheia da passagem (rota x classe), antes de qualquer desconto. */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal tarifaBase;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal desconto = BigDecimal.ZERO;

    /** Valor final da passagem (tarifaBase - desconto). Base de qualquer reembolso. */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorPago;

    public PassagemModels() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public StatusPassagem getStatus() { return status; }
    public void setStatus(StatusPassagem status) { this.status = status; }

    public VendaModels getVenda() { return venda; }
    public void setVenda(VendaModels venda) { this.venda = venda; }

    public long getIdVenda() { return idVenda; }
    public void setIdVenda(long idVenda) { this.idVenda = idVenda; }

    public TipoTrecho getTipoTrecho() { return tipoTrecho; }
    public void setTipoTrecho(TipoTrecho tipoTrecho) { this.tipoTrecho = tipoTrecho; }

    public PassagemModels getPassagemVinculada() { return passagemVinculada; }
    public void setPassagemVinculada(PassagemModels passagemVinculada) { this.passagemVinculada = passagemVinculada; }

    public Long getIdPassagemVinculada() { return idPassagemVinculada; }
    public void setIdPassagemVinculada(Long idPassagemVinculada) { this.idPassagemVinculada = idPassagemVinculada; }

    public ViagemModels getViagem() { return viagem; }
    public void setViagem(ViagemModels viagem) { this.viagem = viagem; }

    public long getIdViagem() { return idViagem; }
    public void setIdViagem(long idViagem) { this.idViagem = idViagem; }

    public AssentoModels getAssento() { return assento; }
    public void setAssento(AssentoModels assento) { this.assento = assento; }

    public long getIdAssento() { return idAssento; }
    public void setIdAssento(long idAssento) { this.idAssento = idAssento; }

    public PassageiroModels getPassageiro() { return passageiro; }
    public void setPassageiro(PassageiroModels passageiro) { this.passageiro = passageiro; }

    public long getIdPassageiro() { return idPassageiro; }
    public void setIdPassageiro(long idPassageiro) { this.idPassageiro = idPassageiro; }

    public LocalDate getDataPassagem() { return dataPassagem; }
    public void setDataPassagem(LocalDate dataPassagem) { this.dataPassagem = dataPassagem; }

    public LocalTime getHoraPassagem() { return horaPassagem; }
    public void setHoraPassagem(LocalTime horaPassagem) { this.horaPassagem = horaPassagem; }

    public String getOrigem() { return origem; }
    public void setOrigem(String origem) { this.origem = origem; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public BigDecimal getDistancia() { return distancia; }
    public void setDistancia(BigDecimal distancia) { this.distancia = distancia; }

    public TipoTarifa getTipoTarifa() { return tipoTarifa; }
    public void setTipoTarifa(TipoTarifa tipoTarifa) { this.tipoTarifa = tipoTarifa; }

    public BigDecimal getTarifaBase() { return tarifaBase; }
    public void setTarifaBase(BigDecimal tarifaBase) { this.tarifaBase = tarifaBase; }

    public BigDecimal getDesconto() { return desconto; }
    public void setDesconto(BigDecimal desconto) { this.desconto = desconto; }

    public BigDecimal getValorPago() { return valorPago; }
    public void setValorPago(BigDecimal valorPago) { this.valorPago = valorPago; }
}
