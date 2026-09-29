package com.johnwilliam.ExpressoUnix.Exceptions;

/** Violacao de regra de negocio ou dado invalido (HTTP 400). */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
