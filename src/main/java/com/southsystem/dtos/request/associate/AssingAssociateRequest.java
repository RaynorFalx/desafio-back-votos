package com.southsystem.dtos.request.associate;

import io.swagger.annotations.ApiModelProperty;

public interface AssingAssociateRequest {

    @ApiModelProperty(
            name = "CPF identificador do Associdado",
            notes = "associateCpf.",
            example = "73403148149",
            required = true
    )
    Long getAssociateCpf();

    @ApiModelProperty(
            name = "Nome identificador do Associdado",
            notes = "associateName.",
            example = "Juliano Afonso Dias Rodrigues",
            required = true
    )
    String getAssociateName();
}
