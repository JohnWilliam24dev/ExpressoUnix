package com.johnwilliam.ExpressoUnix.Exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String recurso, long id) {
        super(recurso + " com id " + id + " nao encontrado(a)");
    }
}
