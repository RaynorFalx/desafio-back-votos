package com.southsystem.dtos.request.schedule;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public interface CreateScheduleRequest {

    @ApiModelProperty(
            name = "Nome da Pauta.",
            notes = "scheduleName.",
            example = "Pauta para Votação de horas extras.",
            required = true
    )
    String getScheduleName();

    @ApiModelProperty(
            name = "Descrição da Pauta.",
            notes = "scheduleDescription.",
            example = "Pauta para Votação do ajuste de horas extras em 3%.",
            required = true
    )
    String getScheduleDescription();
}
