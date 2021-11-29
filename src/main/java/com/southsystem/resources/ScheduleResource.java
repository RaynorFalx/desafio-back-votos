package com.southsystem.resources;

import com.southsystem.domain.Associate;
import com.southsystem.domain.Schedule;
import com.southsystem.dtos.request.schedule.CreateScheduleRequestImpl;
import com.southsystem.dtos.response.DefaultRequestResponseImpl;
import com.southsystem.repositories.ScheduleRepository;
import com.southsystem.services.ScheduleService;
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
        tags = {"Recursos de Pauta"},
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
)
@RestController
public class ScheduleResource {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @ApiOperation(
            value = "Cadastrar uma nova Pauta a partir de um nome e uma Descrição",
            response = DefaultRequestResponseImpl.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Requisição bem sucedida, retorna o status da requisição HTTP e uma mensagem de descrição",
                    response = DefaultRequestResponseImpl.class
            )
    })
    @PostMapping(path = "/createSchedule")
    public DefaultRequestResponseImpl createSchedule(@Valid @RequestBody CreateScheduleRequestImpl requestBody) {
        return scheduleService.createSchedule(requestBody);
    }

    @ApiOperation(
            value = "Consultar uma Pauta a partir do seu ID",
            response = Schedule.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Requisição bem sucedida, uma entidade: Pauta",
                    response = Schedule.class
            ),
            @ApiResponse(
                    code = 404,
                    message = "Requisição má sucedida, não há Pautas na base de dados com o ID requisitado.",
                    response = SouthSystemErro.class
            )
    })
    @GetMapping(path = "/schedule/{id}")
    public Schedule findScheduleById(@PathVariable("id") Long scheduleId) {
        return scheduleService.findScheduleById(scheduleId);
    }
}
