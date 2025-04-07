package com.johnwilliam.ExpressoUnix.Entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import com.johnwilliam.ExpressoUnix.Enums.StatusPassagem;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"id_viagem", "id_assento"})})
public class Passagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPassagem status;

    @ManyToOne
    @JoinColumn(name = "id_viagem",  referencedColumnName = "id", insertable = false, updatable = false)
    private Viagem viagem;
    
    @Column(name = "id_viagem", nullable = false)
    private long idViagem;

    @ManyToOne
    @JoinColumn(name = "id_assento", referencedColumnName = "id", insertable = false, updatable = false)
    private Assento assento;
    
    @Column(name = "id_assento", nullable = false)
    private long idAssento;

    @ManyToOne
    @JoinColumn(name = "id_passageiro",  referencedColumnName = "id", insertable = false, updatable = false)
    private Passageiro passageiro;
    
    @Column(name = "id_passageiro", nullable = false)
    private long idPassageiro;

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

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    public Passagem() {}

    public Passagem(StatusPassagem status, Viagem viagem, Assento assento, Passageiro passageiro,
                    LocalDate dataPassagem, LocalTime horaPassagem, String origem, String destino,
                    BigDecimal distancia, BigDecimal preco) {
        this.status = status;
        this.viagem = viagem;
        this.idViagem = (viagem != null) ? viagem.getId() : 0;
        this.assento = assento;
        this.idAssento = (assento != null) ? assento.getId() : 0;
        this.passageiro = passageiro;
        this.idPassageiro = (passageiro != null) ? passageiro.getId() : 0;
        this.dataPassagem = dataPassagem;
        this.horaPassagem = horaPassagem;
        this.origem = origem;
        this.destino = destino;
        this.distancia = distancia;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public StatusPassagem getStatus() {
        return status;
    }
    public void setStatus(StatusPassagem status) {
        this.status = status;
    }

    // Apenas getters e setters dos identificadores
    public long getIdViagem() {
        return idViagem;
    }
    public void setIdViagem(long idViagem) {
        this.idViagem = idViagem;
    }

    public long getIdAssento() {
        return idAssento;
    }
    public void setIdAssento(long idAssento) {
        this.idAssento = idAssento;
    }

    public long getIdPassageiro() {
        return idPassageiro;
    }
    public void setIdPassageiro(long idPassageiro) {
        this.idPassageiro = idPassageiro;
    }

    public LocalDate getDataPassagem() {
        return dataPassagem;
    }
    public void setDataPassagem(LocalDate dataPassagem) {
        this.dataPassagem = dataPassagem;
    }

    public LocalTime getHoraPassagem() {
        return horaPassagem;
    }
    public void setHoraPassagem(LocalTime horaPassagem) {
        this.horaPassagem = horaPassagem;
    }

    public String getOrigem() {
        return origem;
    }
    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }

    public BigDecimal getDistancia() {
        return distancia;
    }
    public void setDistancia(BigDecimal distancia) {
        this.distancia = distancia;
    }

    public BigDecimal getPreco() {
        return preco;
    }
    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
