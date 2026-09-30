package com.johnwilliam.ExpressoUnix.DTO;

import java.math.BigDecimal;

import com.johnwilliam.ExpressoUnix.Enums.Classe;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class TabelaPrecoDTO {
    /** Somente saida: na atualizacao a classe vem da URL. */
    private Classe classe;

    @NotNull(message = "multiplicador e obrigatorio")
    @Positive(message = "multiplicador deve ser maior que zero")
    @Digits(integer = 3, fraction = 2, message = "multiplicador aceita ate 3 inteiros e 2 decimais")
    private BigDecimal multiplicador;

    public TabelaPrecoDTO() {}

    public TabelaPrecoDTO(Classe classe, BigDecimal multiplicador) {
        this.classe = classe;
        this.multiplicador = multiplicador;
    }

    public Classe getClasse() { return classe; }
    public void setClasse(Classe classe) { this.classe = classe; }

    public BigDecimal getMultiplicador() { return multiplicador; }
    public void setMultiplicador(BigDecimal multiplicador) { this.multiplicador = multiplicador; }
}
