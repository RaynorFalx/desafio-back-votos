package com.southsystem.resources;

import com.southsystem.dtos.request.vote.MakeVoteRequestImpl;
import com.southsystem.dtos.response.DefaultRequestResponseImpl;
import com.southsystem.services.VoteService;
import com.southsystem.utils.exceptions.SouthSystemErro;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(
        tags = {"Recursos de Votos"},
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
)
@RestController
public class VoteResource {

    @Autowired
    private VoteService voteService;

    @ApiOperation(
            value = "Realizar voto em uma Pauta a que não tenha sido encerrada",
            response = DefaultRequestResponseImpl.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Requisição bem sucedida, retorna o status da requisição HTTP e uma mensagem de descrição",
                    response = DefaultRequestResponseImpl.class
            ),
            @ApiResponse(
                    code = 400,
                    message = "Requisição má sucedida, o validador de CPF determinou que o Assciado não está apto para " +
                            "executar um voto. | A seção de votação teve seu tempo expirado." +
                            "| O Associado não pode votar mais de uma vez",
                    response = SouthSystemErro.class
            )
    })
    @PostMapping(path = "/makeVote")
    public DefaultRequestResponseImpl makeVote(@Valid @RequestBody MakeVoteRequestImpl requestBody) {
        return voteService.makeVote(requestBody);
    }
}
