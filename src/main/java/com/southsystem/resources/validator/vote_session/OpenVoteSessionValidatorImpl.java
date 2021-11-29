package com.southsystem.resources.validator.vote_session;

import com.southsystem.dtos.request.vote_session.OpenVoteSessionRequestImpl;
import com.southsystem.services.utils.JsonService;
import com.southsystem.utils.exceptions.SouthSystemErro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OpenVoteSessionValidatorImpl
        implements ConstraintValidator<OpenVoteSessionValidator, OpenVoteSessionRequestImpl> {

    @Autowired
    private JsonService jsonService;

    private static final String DATA_PATTERN = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";

    @Override
    public boolean isValid(OpenVoteSessionRequestImpl requestBody, ConstraintValidatorContext constraintValidatorContext) {

        var sequential = 0;
        var badRequestCode = String.valueOf(HttpStatus.BAD_REQUEST.value());
        var message = "O campo é obrigatório: ";
        var closingDate = requestBody.getVoteSessionClosingDate();
        List<SouthSystemErro> errors = new ArrayList<>();

        if (Objects.isNull(requestBody.getScheduleId())) {
            errors.add(
                    new SouthSystemErro.Builder(badRequestCode, message.concat("scheduleId"))
                            .userHelp("Exemplo: {scheduleId: 1}")
                            .sequential(String.valueOf(++sequential))
                            .msgCorrelationId("scheduleId")
                            .category("MethodArgumentNotValidException")
                            .developerMessage("O campo da requisição é obrigatório.")
                            .build()
            );
        }

        if(Objects.isNull(requestBody.getVoteSessionDescription())) {
            errors.add(
                    new SouthSystemErro.Builder(badRequestCode, message.concat("voteSessionDescription"))
                            .userHelp("Exemplo: {voteSessionDescription: Seção de Votação que inclui a Pauta de Revisão de Horas extras}")
                            .sequential(String.valueOf(++sequential))
                            .msgCorrelationId("voteSessionDescription")
                            .category("MethodArgumentNotValidException")
                            .developerMessage("O campo da requisição é obrigatório.")
                            .build()
            );
        }

        if (closingDate.isPresent() && !closingDate.get().matches(DATA_PATTERN)) {
            errors.add(
                    new SouthSystemErro.Builder(badRequestCode, "Campo com má formatação")
                            .userHelp("Exemplo: {voteSessionClosingDate: 2021-12-31 12:00:00}")
                            .sequential(String.valueOf(++sequential))
                            .msgCorrelationId("voteSessionClosingDate")
                            .category("MethodArgumentNotValidException")
                            .developerMessage("O deve estar no formato yyyy-MM-dd HH:mm:ss.")
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
