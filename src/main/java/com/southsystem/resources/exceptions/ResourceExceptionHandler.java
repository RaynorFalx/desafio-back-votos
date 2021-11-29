package com.southsystem.resources.exceptions;

import com.southsystem.services.utils.JsonService;
import com.southsystem.utils.exceptions.SouthSystemDefaultErro;
import com.southsystem.utils.exceptions.SouthSystemErro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @Autowired
    private JsonService jsonService;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<SouthSystemDefaultErro> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        var error = new SouthSystemDefaultErro();

        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
                    var msg = jsonService.parse(fieldError.getDefaultMessage(), SouthSystemErro.class);
                    error.addErro(msg);
                });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<SouthSystemDefaultErro> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        var error = new SouthSystemDefaultErro();

        var mensagemErro = new SouthSystemErro.Builder(
                String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()),
                "Método requisitado " + exception.getMethod() + " não suportado!"
        ).sequential("1")
                .userHelp("Métodos suportados: " + exception.getSupportedHttpMethods())
                .msgCorrelationId("httpMethod")
                .category("METHOD_NOT_ALLOWED")
                .developerMessage("Esse recurso está disponível via POST.")
                .build();


        error.getErros().add(mensagemErro);
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<SouthSystemDefaultErro> httpMessageNotReadableException(
            HttpMessageNotReadableException exception,
            HttpServletRequest request
    ) {
        var error = new SouthSystemDefaultErro();

        var mensagemDeErro = new SouthSystemErro.Builder(
                String.valueOf(HttpStatus.CONFLICT.value()),
                "Corpo da Requisição não legível."
        ).sequential("0")
                .userHelp("Consulte a documentação em {dominio}/swagger-ui.html.")
                .msgCorrelationId("HTTP_REQUEST")
                .category("CONFLICT")
                .developerMessage("O corpo da requisição não pode ser processado, verificar na documentação.")
                .build();

        error.getErros().add(mensagemDeErro);
        return ResponseEntity.badRequest().body(error);
    }
}
