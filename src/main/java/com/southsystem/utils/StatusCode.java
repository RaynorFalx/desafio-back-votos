package com.southsystem.utils;

import java.util.Arrays;

public enum StatusCode {

    OK("200", "Operação recebida."),
    CREATED("201", "Um novo recurso foi criado com sucesso.");

    final String code;
    final String description;

    StatusCode(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    public static StatusCode toEnum(String statusCode) {
        return Arrays.stream(StatusCode.values())
                .filter(x -> x.getCode().equals(statusCode)).findFirst().orElseThrow(RuntimeException::new);
    }

    public String getCode() {
        return code;
    }

    public String getdDescription() {
        return description;
    }
}

