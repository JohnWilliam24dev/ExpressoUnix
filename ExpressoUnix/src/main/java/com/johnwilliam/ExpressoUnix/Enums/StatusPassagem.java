package com.johnwilliam.ExpressoUnix.Enums;

/**
 * Ciclo de vida da passagem (substitui o antigo Valido/Invalido).
 * Fase 1 usa Emitida, Utilizada e Cancelada; Reservada/Expirada entram com a reserva
 * temporaria (fase 2) e Remarcada com a remarcacao (fase 3).
 */
public enum StatusPassagem {
    Reservada,
    Emitida,
    Utilizada,
    Cancelada,
    Remarcada,
    Expirada
}
