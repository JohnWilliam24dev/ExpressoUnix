package com.johnwilliam.ExpressoUnix.DTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ViagemDTO {
    private Long id;
    private long idVeiculo;
    private LocalDate dataViagem;
    private LocalTime horaViagem;
    private String origem;
    private String destino;
    private VeiculoDTO veiculo;
    private List<AssentoDTO> assentos;
    private List<PassagemDTO> passagens;

    public ViagemDTO() {}

    public ViagemDTO(VeiculoDTO veiculo, LocalDate dataViagem, LocalTime horaViagem, String origem, String destino) {
        this.veiculo = veiculo;
        this.idVeiculo = (veiculo != null) ? veiculo.getId() : 0;
        this.dataViagem = dataViagem;
        this.horaViagem = horaViagem;
        this.origem = origem;
        this.destino = destino;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public long getIdVeiculo() { return idVeiculo; }
    public void setIdVeiculo(long idVeiculo) { this.idVeiculo = idVeiculo; }

    public LocalDate getDataViagem() { return dataViagem; }
    public void setDataViagem(LocalDate dataViagem) { this.dataViagem = dataViagem; }

    public LocalTime getHoraViagem() { return horaViagem; }
    public void setHoraViagem(LocalTime horaViagem) { this.horaViagem = horaViagem; }

    public String getOrigem() { return origem; }
    public void setOrigem(String origem) { this.origem = origem; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public VeiculoDTO getVeiculo() { return veiculo; }
    public void setVeiculo(VeiculoDTO veiculo) { this.veiculo = veiculo; }

    public List<AssentoDTO> getAssentos() { return assentos; }
    public void setAssentos(List<AssentoDTO> assentos) { this.assentos = assentos; }

    public List<PassagemDTO> getPassagens() { return passagens; }
    public void setPassagens(List<PassagemDTO> passagens) { this.passagens = passagens; }
}
