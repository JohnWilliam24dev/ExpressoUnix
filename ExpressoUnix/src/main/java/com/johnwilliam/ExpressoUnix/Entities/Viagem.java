package com.johnwilliam.ExpressoUnix.Entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.johnwilliam.ExpressoUnix.Entities.Objects.Text;

public class Viagem {
    
    private Long id;
    
    private Veiculo veiculo;


    
    private LocalDate dataViagem;
    
    private LocalTime horaViagem;
    
    private Text origem;

    private Text destino;
    
    private List<Assento> assentos;
    
    private List<Passagem> passagens;

    

    public Viagem(Veiculo veiculo, LocalDate dataViagem, LocalTime horaViagem, String origem, String destino) {
        this.veiculo = veiculo;
        
        this.dataViagem = dataViagem;
        this.horaViagem = horaViagem;
        this.origem = new Text(origem);
        this.destino = new Text(destino);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDataViagem() { return dataViagem; }
    public void setDataViagem(LocalDate dataViagem) { this.dataViagem = dataViagem; }

    public LocalTime getHoraViagem() { return horaViagem; }
    public void setHoraViagem(LocalTime horaViagem) { this.horaViagem = horaViagem; }

    public String getOrigem() { return origem.toString(); }
    public void setOrigem(String origem) { this.origem.setText(origem); }

    public String getDestino() { return destino.toString(); }
    public void setDestino(String destino) { this.destino.setText(destino); }

    
}