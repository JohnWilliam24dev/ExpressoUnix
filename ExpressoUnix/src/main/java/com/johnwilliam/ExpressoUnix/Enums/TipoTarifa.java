package com.johnwilliam.ExpressoUnix.Enums;

import java.math.BigDecimal;

/**
 * Tipo de tarifa aplicado sobre o preco da rota.
 * Na fase 1 existe apenas a tarifa inteira; meia e gratuidade entram na fase 2 (VEN-06).
 */
public enum TipoTarifa {
    INTEIRA(new BigDecimal("1.00"));

    private final BigDecimal percentual;

    TipoTarifa(BigDecimal percentual) {
        this.percentual = percentual;
    }

    public BigDecimal getPercentual() {
        return percentual;
    }
}
