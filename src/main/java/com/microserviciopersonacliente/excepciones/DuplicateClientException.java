package com.microserviciopersonacliente.excepciones;

public class DuplicateClientException extends RuntimeException {
    public DuplicateClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
