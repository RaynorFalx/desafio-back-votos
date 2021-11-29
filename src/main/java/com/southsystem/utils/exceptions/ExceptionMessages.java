package com.southsystem.utils.exceptions;

import org.springframework.http.HttpStatus;

public enum ExceptionMessages {

    SOUTH_SYSTEM_RUNTIME_ERRO(
            String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
            "O servidor encontrou alguma condição inesperada", "0",
            "Consultar a documentação em {dominio}/swagger-ui.html.",
            "INTERNAL_SERVER_ERROR",
            "RunTimeExeption",
            "O servidor encontrou um erro durante o tempo de execução da API. É necessário uma " +
                    "verificação do Stack Trace do erro para averiguar a condição ocorrida."
    ),

    SOUTH_SYSTEM_SCHEDULE_NOT_FOUND(
            String.valueOf(HttpStatus.NOT_FOUND.value()),
            "Pauta não encontrada", "0",
            "Consultar a documentação em {dominio}/swagger-ui.html.",
            "NOT_FOUND",
            "RunTimeExeption",
            "A API não encontrou um recurso necessário para a execução. Verificar com o administrador " +
                    "de dados se o recurso exsite na base."
    ),

    SOUTH_SYSTEM_ASSOCIATE_NOT_FOUND(
            String.valueOf(HttpStatus.NOT_FOUND.value()),
            "Associado não encontrado", "0",
            "Consultar a documentação em {dominio}/swagger-ui.html.",
            "ASSOCIATE_NOT_FOUND",
            "RunTimeExeption",
            "A API não encontrou um recurso necessário para a execução. Verificar com o administrador " +
                    "de dados se o recurso existe na base."
    ),

    SOUTH_SYSTEM_ASSOCIATE_CONFLICT(
            String.valueOf(HttpStatus.CONFLICT.value()),
            "Associado já está cadastrado", "0",
            "Consultar a documentação em {dominio}/swagger-ui.html.",
            "ASSOCIATE_CONFLICT",
            "RunTimeExeption",
            "A API encontrou um conflito nos recursos do banco de dados. Verificar com o administrador" +
                    "de dados o recurso desejado."
    ),

    SOUTH_SYSTEM_ASSOCIATE_UNABLE_TO_VOTE(
            String.valueOf(HttpStatus.BAD_REQUEST.value()),
            "De acordo com a validação de CPF o associado não é capaz de votar", "0",
            "Consultar a documentação em {dominio}/swagger-ui.html.",
            "ASSOCIATE_BAD_REQUEST",
            "RunTimeExeption",
            "O validador de CPF determinou que o associado não pode votar, verificar estrutura do CPF."
    ),

    SOUTH_SYSTEM_VOTE_SESSION_NOT_FOUND(
            String.valueOf(HttpStatus.CONFLICT.value()),
            "Seção de Votação não encontrada", "0",
            "Consultar a documentação em {dominio}/swagger-ui.html.",
            "VOTE_SESSION_NOT_FOUND",
            "RunTimeExeption",
            "A API não encontrou um recurso necessário para a execução. Verificar com o administrador " +
            "de dados se o recurso existe na base."
    ),

    SOUTH_SYSTEM_VOTE_SESSION_EXPIRED(
            String.valueOf(HttpStatus.BAD_REQUEST.value()),
            "Seção de Votação está encerrada", "0",
            "Consultar a documentação em {dominio}/swagger-ui.html.",
            "VOTE_SESSION_EXPIRED",
            "RunTimeExeption",
            "A Seção de Votação para essa Pauta teve seu tempo de expirado, verifique com os" +
                    "administradores do sistema os tempos de válidade de cada Seção de Votação."
    ),

    SOUTH_SYSTEM_VOTE_SESSION_ALREADY_VOTED(
            String.valueOf(HttpStatus.BAD_REQUEST.value()),
            "Esse associado já votou nessa Seção de Votos", "0",
            "Consultar a documentação em {dominio}/swagger-ui.html.",
            "VOTE_SESSION_ALREADY_VOTED",
            "RunTimeExeption",
            "Cada Asscoiado deve votar apenas uma vez por Seção de Votação."
    ),

    SOUTH_SYSTEM_VOTE_SESSION_BAD_CLOSING_DATE(
            String.valueOf(HttpStatus.BAD_REQUEST.value()),
            "A data de fechamento da seção está mal formatada", "0",
            "Consultar a documentação em {dominio}/swagger-ui.html.",
            "VOTE_SESSION_BAD_CLOSING_DATE",
            "RunTimeExeption",
            "A data de formatação da votação está mal formatada, exemplo: 2021-12-31 12:00:00"
    ),

    SOUTH_SYSTEM_HTTP_CLIENT_ERROR(
            String.valueOf(HttpStatus.BAD_REQUEST.value()),
            "Houve um erro inesperado durante a comunicação com um componenete externo", "0",
            "Consultar a documentação em {dominio}/swagger-ui.html.",
            "SOUTH_SYSTEM_HTTP_CLIENT_ERROR",
            "HttpClientErrorException",
            "Houve uma condição inesperada durante a comunicação com o validador de CPF, uma URI " +
                    "inexistente devido a um CPF mal formatado ou falha durante o GET do validador"
    );


    String code;
    String message;
    String sequential;
    String userHelp;
    String msgCorrelationId;
    String category;
    String developerMessage;

    ExceptionMessages(String code, String message, String sequential, String userHelp,
                      String msgCorrelationId, String category, String developerMessage) {
        this.code = code;
        this.message = message;
        this.sequential = sequential;
        this.userHelp = userHelp;
        this.msgCorrelationId = msgCorrelationId;
        this.category = category;
        this.developerMessage = developerMessage;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getSequential() {
        return sequential;
    }

    public String getUserHelp() {
        return userHelp;
    }

    public String getMsgCorrelationId() {
        return msgCorrelationId;
    }

    public String getCategory() {
        return category;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }
}
