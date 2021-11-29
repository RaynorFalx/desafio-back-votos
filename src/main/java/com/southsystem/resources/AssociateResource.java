package com.southsystem.resources;

import com.southsystem.domain.Associate;
import com.southsystem.dtos.request.associate.AssingAssociateRequestImpl;
import com.southsystem.dtos.response.DefaultRequestResponseImpl;
import com.southsystem.services.AssociateService;
import com.southsystem.utils.exceptions.SouthSystemErro;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;

@Api(
        tags = {"Recursos de Associado"},
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
)
@RestController
public class AssociateResource {

    @Autowired
    private AssociateService associateService;

    @ApiOperation(
            value = "Cadastrar um novo Associado a partir do seu CPF e Nome",
            response = DefaultRequestResponseImpl.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Requisição bem sucedida, retorna o status da requisição HTTP e uma mensagem de descrição",
                    response = DefaultRequestResponseImpl.class
            ),
            @ApiResponse(
                    code = 409,
                    message = "Requisição má sucedida, conflito de inserção com usuários já existentes na base. Retorna " +
                            "um corpo JSON com a descrição do erro.",
                    response = SouthSystemErro.class
            )
    })
    @PostMapping(path = "/assingAssociate")
    public DefaultRequestResponseImpl assingAssociate(@Valid @RequestBody AssingAssociateRequestImpl requestBody) {
        return associateService.assingAssociate(requestBody);
    }

    @ApiOperation(
            value = "Consultar um Associado a partir do seu CPF",
            response = Associate.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Requisição bem sucedida, uma entidade: Associado",
                    response = Associate.class
            ),
            @ApiResponse(
                    code = 404,
                    message = "Requisição má sucedida, não há Associados na base de dados com o CPF requisitado.",
                    response = SouthSystemErro.class
            )
    })
    @GetMapping(path = "/associate/{cpf}")
    public Associate findAssociateByCPF(@PathVariable("cpf") Long cpf) {
        return associateService.findAssociateByCPF(cpf);
    }
}
