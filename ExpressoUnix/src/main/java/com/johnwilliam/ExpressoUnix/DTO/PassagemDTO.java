package com.johnwilliam.ExpressoUnix.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import com.johnwilliam.ExpressoUnix.Enums.StatusPassagem;

public class PassagemDTO {
    private Long id;
    private StatusPassagem status;
    private long idViagem;
    private long idAssento;
    private long idPassageiro;
    private LocalDate dataPassagem;
    private LocalTime horaPassagem;
    private String origem;
    private String destino;
    private BigDecimal distancia;
    private BigDecimal preco;
    private ViagemDTO viagem;
    private AssentoDTO assento;
    private PassageiroDTO passageiro;
    private List<VendaDTO> vendas;

    public PassagemDTO() {}

    public PassagemDTO(StatusPassagem status, long viagem, long assento, long passageiro,
                       LocalDate dataPassagem, LocalTime horaPassagem, String origem, String destino,
                       BigDecimal distancia, BigDecimal preco) {
        this.status = status;
        this.idViagem = viagem;
        this.idAssento = assento;
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

    public ViagemDTO getViagem() { return viagem; }
    public void setViagem(ViagemDTO viagem) { this.viagem = viagem; }

    public AssentoDTO getAssento() { return assento; }
    public void setAssento(AssentoDTO assento) { this.assento = assento; }

    public PassageiroDTO getPassageiro() { return passageiro; }
    public void setPassageiro(PassageiroDTO passageiro) { this.passageiro = passageiro; }

    public List<VendaDTO> getVendas() { return vendas; }
    public void setVendas(List<VendaDTO> vendas) { this.vendas = vendas; }
}
