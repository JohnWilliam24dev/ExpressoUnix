package com.johnwilliam.ExpressoUnix.DTO;

import java.util.List;
import com.johnwilliam.ExpressoUnix.Enums.StatusAssento;

public class AssentoDTO {
    private Long id;
    private long idViagem;
    private int numeroAssento;
    private StatusAssento statusAssento = StatusAssento.Livre;
    private List<PassagemDTO> passagens;
    private ViagemDTO viagem;

    public AssentoDTO() {}

    public AssentoDTO(ViagemDTO viagem, int numeroAssento, StatusAssento statusAssento) {
        this.viagem = viagem;
        this.idViagem = (viagem != null) ? viagem.getId() : 0;
        this.numeroAssento = numeroAssento;
        this.statusAssento = statusAssento;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public long getIdViagem() { return idViagem; }
    public void setIdViagem(long idViagem) { this.idViagem = idViagem; }

    public int getNumeroAssento() { return numeroAssento; }
    public void setNumeroAssento(int numeroAssento) { this.numeroAssento = numeroAssento; }

    public StatusAssento getStatusAssento() { return statusAssento; }
    public void setStatusAssento(StatusAssento statusAssento) { this.statusAssento = statusAssento; }

    public List<PassagemDTO> getPassagens() { return passagens; }
    public void setPassagens(List<PassagemDTO> passagens) { this.passagens = passagens; }

    public ViagemDTO getViagem() { return viagem; }
    public void setViagem(ViagemDTO viagem) { this.viagem = viagem; }
}
