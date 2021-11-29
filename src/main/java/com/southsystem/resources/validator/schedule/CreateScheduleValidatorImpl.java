package com.southsystem.resources.validator.schedule;

import com.southsystem.dtos.request.schedule.CreateScheduleRequestImpl;
import com.southsystem.services.utils.JsonService;
import com.southsystem.utils.exceptions.SouthSystemErro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreateScheduleValidatorImpl
        implements ConstraintValidator<CreateScheduleValidator, CreateScheduleRequestImpl> {

    @Autowired
    private JsonService jsonService;

    @Override
    public boolean isValid(CreateScheduleRequestImpl requestBody, ConstraintValidatorContext constraintValidatorContext) {

        var sequential = 0;
        var badRequestCode = String.valueOf(HttpStatus.BAD_REQUEST.value());
        var message = "O campo é obrigatório: ";
        List<SouthSystemErro> errors = new ArrayList<>();

        if (Objects.isNull(requestBody.getScheduleName())) {
            errors.add(
                    new SouthSystemErro.Builder(badRequestCode, message.concat("scheduleName"))
                            .userHelp("Exemplo: {scheduleName: Pauta 367 - Revisão de Horas extras.}")
                            .sequential(String.valueOf(++sequential))
                            .msgCorrelationId("scheduleName")
                            .category("MethodArgumentNotValidException")
                            .developerMessage("O campo da requisição é obrigatório.")
                            .build()
            );
        }

        if (Objects.isNull(requestBody.getScheduleDescription())) {
            errors.add(
                    new SouthSystemErro.Builder(badRequestCode, message.concat("scheduleDescription"))
                            .userHelp("Exemplo: {scheduleDescription: Revisão dos valores das horas extras.}")
                            .sequential(String.valueOf(++sequential))
                            .msgCorrelationId("scheduleDescription")
                            .category("MethodArgumentNotValidException")
                            .developerMessage("O campo da requisição é obrigatório.")
                            .build()
            );
        }

        errors.forEach(erroMessage -> {
            var strObj = jsonService.stringify(erroMessage);

            constraintValidatorContext.disableDefaultConstraintViolation();

            constraintValidatorContext.buildConstraintViolationWithTemplate(strObj)
                    .addPropertyNode(erroMessage.getMsgCorrelationId())
                    .addConstraintViolation();
        });

        return errors.isEmpty();
    }
}
