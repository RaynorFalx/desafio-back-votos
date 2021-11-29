package com.southsystem.resources.validator.vote;

import com.southsystem.dtos.request.vote.MakeVoteRequestImpl;
import com.southsystem.services.utils.JsonService;
import com.southsystem.utils.exceptions.SouthSystemErro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MakeVoteValidatorImpl
        implements ConstraintValidator<MakeVoteValidator, MakeVoteRequestImpl> {

    @Autowired
    private JsonService jsonService;

    @Override
    public boolean isValid(MakeVoteRequestImpl requestBody, ConstraintValidatorContext constraintValidatorContext) {

        var sequential = 0;
        var badRequestCode = String.valueOf(HttpStatus.BAD_REQUEST.value());
        var message = "O campo é obrigatório: ";
        List<SouthSystemErro> errors = new ArrayList<>();

        if (Objects.isNull(requestBody.getVoteSessionId())) {
            errors.add(
                    new SouthSystemErro.Builder(badRequestCode, message.concat("voteSessionId"))
                            .userHelp("Exemplo: {voteSessionId: 1.}")
                            .sequential(String.valueOf(++sequential))
                            .msgCorrelationId("voteSessionId")
                            .category("MethodArgumentNotValidException")
                            .developerMessage("O campo da requisição é obrigatório.")
                            .build()
            );
        }

        if (Objects.isNull(requestBody.getAssociateCpf())) {
            errors.add(
                    new SouthSystemErro.Builder(badRequestCode, message.concat("associateCpf"))
                            .userHelp("Exemplo: {associateCpf: 73403148149.}")
                            .sequential(String.valueOf(++sequential))
                            .msgCorrelationId("associateCpf")
                            .category("MethodArgumentNotValidException")
                            .developerMessage("O campo da requisição é obrigatório.")
                            .build()
            );
        }

        if (Objects.isNull(requestBody.getVote())) {
            errors.add(
                    new SouthSystemErro.Builder(badRequestCode, message.concat("vote"))
                            .userHelp("Exemplo: {vote: S.}")
                            .sequential(String.valueOf(++sequential))
                            .msgCorrelationId("vote")
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
