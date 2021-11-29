package com.southsystem.utils.exceptions;

public class SouthSystemException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SouthSystemException(String mensagem) {
        super(mensagem);
    }

    public SouthSystemException(String mensagem, Throwable causa) { super(mensagem, causa); }

}
