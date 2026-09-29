package com.johnwilliam.ExpressoUnix.Exceptions;

/** Conflito com o estado atual do recurso, ex.: assento ja ocupado (HTTP 409). */
public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }
}
