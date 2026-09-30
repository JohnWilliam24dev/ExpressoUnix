package com.johnwilliam.ExpressoUnix.Applications;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.johnwilliam.ExpressoUnix.Enums.TipoTarifa;

class PrecoApplicationTest {

    @Test
    void convencionalInteiraMantemPrecoBase() {
        BigDecimal preco = PrecoApplication.calcularPreco(
                new BigDecimal("45.00"), new BigDecimal("1.00"), TipoTarifa.INTEIRA);
        assertEquals(new BigDecimal("45.00"), preco);
    }

    @Test
    void aplicaMultiplicadorDaClasse() {
        BigDecimal preco = PrecoApplication.calcularPreco(
                new BigDecimal("80.00"), new BigDecimal("1.25"), TipoTarifa.INTEIRA);
        assertEquals(new BigDecimal("100.00"), preco);
    }

    @Test
    void arredondaParaDuasCasasHalfUp() {
        // 33.33 x 1.50 = 49.995 -> 50.00
        BigDecimal preco = PrecoApplication.calcularPreco(
                new BigDecimal("33.33"), new BigDecimal("1.50"), TipoTarifa.INTEIRA);
        assertEquals(new BigDecimal("50.00"), preco);
    }
}
