package com.tecnodata.loja.exceptions;

public class TecnodataException extends RuntimeException {

    public TecnodataException(String message) {
        super(message);
    }

    public TecnodataException(String message, Throwable cause) {
        super(message, cause);
    }
}
