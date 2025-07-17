package com.johnwilliam.ExpressoUnix.Models;


import com.johnwilliam.ExpressoUnix.Enums.StatusPassagem;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"id_viagem", "id_assento"})})

public class PassagemModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPassagem status;

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

    @OneToMany(mappedBy = "passagem", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<VendaModels> vendas;

    public PassagemModels() {}

    public PassagemModels(StatusPassagem status, long viagem, long assento, long passageiro,
                    LocalDate dataPassagem, LocalTime horaPassagem, String origem, String destino,
                    BigDecimal distancia, BigDecimal preco) {
        this.status = status;
       
        this.idViagem = viagem ;
        
        this.idAssento = assento ;
        
        this.idPassageiro = passageiro;
        this.dataPassagem = dataPassagem;
        this.horaPassagem = horaPassagem;
        this.origem = origem;
        this.destino = destino;
        this.distancia = distancia;
        this.preco = preco;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public StatusPassagem getStatus() { return status; }
    public void setStatus(StatusPassagem status) { this.status = status; }

    public long getIdViagem() { return idViagem; }
    public void setIdViagem(long idViagem) { this.idViagem = idViagem; }

    public long getIdAssento() { return idAssento; }
    public void setIdAssento(long idAssento) { this.idAssento = idAssento; }

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

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }

    public ViagemModels getViagem() {
        return viagem;
    }

    public void setViagem(ViagemModels viagem) {
        this.viagem = viagem;
    }

    public AssentoModels getAssento() {
        return assento;
    }

    public void setAssento(AssentoModels assento) {
        this.assento = assento;
    }

    public PassageiroModels getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(PassageiroModels passageiro) {
        this.passageiro = passageiro;
    }

    public List<VendaModels> getVendas() {
        return vendas;
    }

    public void setVendas(List<VendaModels> vendas) {
        this.vendas = vendas;
    }
    

}
