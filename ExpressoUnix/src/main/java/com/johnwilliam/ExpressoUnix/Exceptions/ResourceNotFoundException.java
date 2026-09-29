package com.johnwilliam.ExpressoUnix.Exceptions;

/** Recurso solicitado nao existe (HTTP 404). */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String recurso, Object id) {
        super(recurso + " com id " + id + " nao encontrado(a)");
    }
}
