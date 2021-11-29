package com.southsystem.resources;

import com.southsystem.domain.Schedule;
import com.southsystem.domain.VoteSession;
import com.southsystem.dtos.request.vote_session.OpenVoteSessionRequestImpl;
import com.southsystem.dtos.response.DefaultRequestResponseImpl;
import com.southsystem.services.VoteSessionService;
import com.southsystem.utils.exceptions.SouthSystemErro;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(
        tags = {"Recursos de Seção de Votação"},
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
)
@RestController
public class VoteSessionResource {

    @Autowired
    private VoteSessionService voteSessionService;

    @ApiOperation(
            value = "Abrir uma Seção de Votação",
            response = DefaultRequestResponseImpl.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Requisição bem sucedida, retorna o status da requisição HTTP e uma mensagem de descrição",
                    response = DefaultRequestResponseImpl.class
            ),
            @ApiResponse(
                    code = 404,
                    message = "Requisição má sucedida, a Pauta enviada para a Seção de Votação não foi encontrada.",
                    response = SouthSystemErro.class
            )
    })
    @PostMapping(path = "/openVoteSession")
    public DefaultRequestResponseImpl openVoteSession(@Valid @RequestBody OpenVoteSessionRequestImpl requestBody) {
        return voteSessionService.openVoteSession(requestBody);
    }

    @ApiOperation(
            value = "Consultar uma Seção de Votação a partir do seu ID",
            response = Schedule.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Requisição bem sucedida, uma entidade: Seção de Votação",
                    response = Schedule.class
            ),
            @ApiResponse(
                    code = 404,
                    message = "Requisição má sucedida, não há Seção de Votação na base de dados com o ID requisitado.",
                    response = SouthSystemErro.class
            )
    })
    @GetMapping(path = "/voteSession/{id}")
    public VoteSession findVoteSessionById(@PathVariable("id") Long voteSessionId) {
        return voteSessionService.findVoteSesseionById(voteSessionId);
    }
}
