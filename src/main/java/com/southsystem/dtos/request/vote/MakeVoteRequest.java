package com.southsystem.dtos.request.vote;

import io.swagger.annotations.ApiModelProperty;

public interface MakeVoteRequest {


    @ApiModelProperty(
            name = "ID da seção de votação",
            notes = "voteSessionId.",
            example = "1",
            required = true
    )
    Long getVoteSessionId();


    @ApiModelProperty(
            name = "CPF do Associado",
            notes = "associateCpf.",
            example = "73403148149",
            required = true
    )
    Long getAssociateCpf();


    @ApiModelProperty(
            name = "Voto do Associado",
            notes = "vote.",
            example = "S/N",
            required = true
    )
    Character getVote();
}
