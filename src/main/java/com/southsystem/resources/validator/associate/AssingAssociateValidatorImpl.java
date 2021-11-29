package com.southsystem.resources.validator.associate;

import com.southsystem.dtos.request.associate.AssingAssociateRequestImpl;
import com.southsystem.services.utils.JsonService;
import com.southsystem.utils.exceptions.SouthSystemErro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AssingAssociateValidatorImpl
        implements ConstraintValidator<AssingAssociateValidator, AssingAssociateRequestImpl> {

    @Autowired
    private JsonService jsonService;

    @Override
    public boolean isValid(AssingAssociateRequestImpl requestBody, ConstraintValidatorContext constraintValidatorContext) {

        var sequential = 0;
        var badRequestCode = String.valueOf(HttpStatus.BAD_REQUEST.value());
        var message = "O campo é obrigatório: ";
        List<SouthSystemErro> errors = new ArrayList<>();

        if (Objects.isNull(requestBody.getAssociateCpf())) {
            errors.add(
                    new SouthSystemErro.Builder(badRequestCode, message.concat("associateCpf"))
                            .userHelp("Exemplo: {associateCpf: 73403148149}")
                            .sequential(String.valueOf(++sequential))
                            .msgCorrelationId("associateCpf")
                            .category("MethodArgumentNotValidException")
                            .developerMessage("O campo da requisição é obrigatório.")
                            .build()
            );
        }

        if (Objects.isNull(requestBody.getAssociateName())) {
            errors.add(
                    new SouthSystemErro.Builder(badRequestCode, message.concat("associateName"))
                            .userHelp("Exemplo: {associateName: Juliano Afonso Dias Rodrigues}")
                            .sequential(String.valueOf(++sequential))
                            .msgCorrelationId("associateName")
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
