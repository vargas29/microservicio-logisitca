package com.microserviciopersonacliente.excepciones;

public class BadRequestExceptionException extends RuntimeException {
    public BadRequestExceptionException(String message) {
        super(message);
    }
}
