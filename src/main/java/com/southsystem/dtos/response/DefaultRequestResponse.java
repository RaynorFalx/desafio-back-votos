package com.southsystem.dtos.response;

import io.swagger.annotations.ApiModelProperty;

public interface DefaultRequestResponse {

    @ApiModelProperty(
            name = "Código do Status HTTP",
            notes = "statusCode.",
            example = "201"
    )
    String getStatusCode();

    @ApiModelProperty(
            name = "Menssagem de descrição do Status",
            notes = "message.",
            example = "Um novo recurso foi criado com sucesso."
    )
    String getMessage();

}
