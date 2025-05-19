package com.johnwilliam.ExpressoUnix.Entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.johnwilliam.ExpressoUnix.Entities.Objects.Text;
import com.johnwilliam.ExpressoUnix.Models.AssentoModels;


public class Viagem {
    
    private Long id;
    
    private Veiculo veiculo;

    private LocalDate dataViagem;
    
    private LocalTime horaViagem;
    
    private Text origem;

    private Text destino;
    
    private List<AssentoModels> assentos;
    
    private List<Passagem> passagens;

    public Viagem(){

    }

    public Viagem(Veiculo veiculo, LocalDate dataViagem, LocalTime horaViagem, String origem, String destino) {
        this.veiculo = veiculo;
        this.dataViagem = dataViagem;
        this.horaViagem = horaViagem;
        this.origem = new Text(origem);
        this.destino = new Text(destino);
    }
    
   

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public LocalDate getDataViagem() {
        return dataViagem;
    }

    public void setDataViagem(LocalDate dataViagem) {
        this.dataViagem = dataViagem;
    }

    public LocalTime getHoraViagem() {
        return horaViagem;
    }

    public void setHoraViagem(LocalTime horaViagem) {
        this.horaViagem = horaViagem;
    }

    public Text getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem.setText(origem);
    }

    public Text getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.setDestino(destino);
    }

    public void disponibilizarAssentos(){
        int capacidade=veiculo.getCapacidade();
        this.assentos= new ArrayList<>();
        for(int i=0;i<=capacidade;i++){
            AssentoModels assento = new AssentoModels();
            assento.setIdViagem(this.id);
            assento.setNumeroAssento(i);
            assentos.add(assento);

        }
    }
    public List<AssentoModels> getAssentos() {
        return assentos;
    }
    
   
    
}