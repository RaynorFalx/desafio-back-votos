package com.southsystem.utils.exceptions;

import io.swagger.annotations.ApiModelProperty;

public class SouthSystemErro {

    @ApiModelProperty(
            name = "code",
            notes = "Código HTTP."
    )
    private String code;

    @ApiModelProperty(
            name = "message",
            notes = "Mensagem de descrição do erro."
    )
    private String message;

    @ApiModelProperty(
            name = "sequential",
            notes = "Sequencial do erro."
    )
    private String sequential = "";

    @ApiModelProperty(
            name = "userHelp",
            notes = "Mensagem de Ajuda ao usuário."
    )
    private String userHelp = "";

    @ApiModelProperty(
            name = "userHelp",
            notes = "Identificação do Status"
    )
    private String msgCorrelationId = "";

    @ApiModelProperty(
            name = "category",
            notes = "Categoria da exceção"
    )
    private String category = "";

    @ApiModelProperty(
            name = "developerMessage",
            notes = "Mensagem do desenvolvedor"
    )
    private String developerMessage = "";

    public SouthSystemErro() {
    }

    public SouthSystemErro(String code, String sequential, String userHelp, String message, String msgCorrelationId, String category, String developerMessage) {
        this.code = code;
        this.sequential = sequential;
        this.userHelp = userHelp;
        this.message = message;
        this.msgCorrelationId = msgCorrelationId;
        this.category = category;
        this.developerMessage =  developerMessage;
    }

    private SouthSystemErro(Builder builder) {
        this.code = builder.code;
        this.sequential = builder.sequential;
        this.message = builder.message;
        this.userHelp = builder.userHelp;
        this.msgCorrelationId = builder.msgCorrelationId;
        this.category = builder.category;
        this.developerMessage = builder.developerMessage;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
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

    public static class Builder {
        private final String code;
        private final String message;
        private String sequential = "";
        private String userHelp = "";
        private String msgCorrelationId = "";
        private String category = "";
        private String developerMessage = "";

        public Builder(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public Builder sequential(String sequential) {
            this.sequential = sequential;
            return this;
        }

        public Builder msgCorrelationId(String msgCorrelationId) {
            this.msgCorrelationId = msgCorrelationId;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder userHelp(String userHelp) {
            this.userHelp = userHelp;
            return this;
        }

        public Builder developerMessage(String developerMessage) {
            this.developerMessage =  developerMessage;
            return this;
        }

        public SouthSystemErro build() {
            return new SouthSystemErro(this);
        }
    }

    public static SouthSystemErro map(ExceptionMessages exceptionMessage) {
        return new SouthSystemErro.Builder(exceptionMessage.getCode(), exceptionMessage.getMessage())
                .sequential(exceptionMessage.getSequential())
                .developerMessage(exceptionMessage.getDeveloperMessage())
                .userHelp(exceptionMessage.getUserHelp())
                .msgCorrelationId(exceptionMessage.getMsgCorrelationId())
                .category(exceptionMessage.getCategory())
                .build();
    }
}
