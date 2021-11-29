package com.southsystem.dtos.request.vote_session;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.Optional;

public interface OpenVoteSessionRequest {

    @ApiModelProperty(
            name = "ID da Pauta a ser votada",
            notes = "scheduleId.",
            example = "1",
            required = true
    )
    Long getScheduleId();

    @ApiModelProperty(
            name = "Descrição da Seção de Votação",
            notes = "voteSessionDescription.",
            example = "Seção de Votação para votar a Pauta de aumento de salário",
            required = true
    )
    String getVoteSessionDescription();

    @ApiModelProperty(
            name = "Data do término da Seção de Votação, do formato yyyy/MM/dd",
            notes = "voteSessionClosingDate.",
            example = "2021-12-31"
    )
    Optional<String> getVoteSessionClosingDate();

}
