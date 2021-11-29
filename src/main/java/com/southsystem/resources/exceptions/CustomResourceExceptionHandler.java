package com.southsystem.resources.exceptions;

import com.southsystem.services.utils.JsonService;
import com.southsystem.utils.exceptions.SouthSystemDefaultErro;
import com.southsystem.utils.exceptions.SouthSystemErro;
import com.southsystem.utils.exceptions.SouthSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.southsystem.utils.exceptions.ExceptionMessages.*;

@ControllerAdvice
public class CustomResourceExceptionHandler {

    @Autowired
    private JsonService jsonService;

    @ExceptionHandler(SouthSystemException.class)
    public ResponseEntity<SouthSystemDefaultErro> customErrosHandler(SouthSystemException exception) {
        var error = new SouthSystemDefaultErro();

        //SCHEDULE HANDLERS//
        if (exception.getMessage().equals(SOUTH_SYSTEM_SCHEDULE_NOT_FOUND.getMsgCorrelationId())) {
            var erroBody = new SouthSystemErro(
                    SOUTH_SYSTEM_SCHEDULE_NOT_FOUND.getCode(), SOUTH_SYSTEM_SCHEDULE_NOT_FOUND.getSequential(),
                    SOUTH_SYSTEM_SCHEDULE_NOT_FOUND.getUserHelp(), SOUTH_SYSTEM_SCHEDULE_NOT_FOUND.getMessage(),
                    SOUTH_SYSTEM_SCHEDULE_NOT_FOUND.getMsgCorrelationId(), SOUTH_SYSTEM_SCHEDULE_NOT_FOUND.getCategory(),
                    SOUTH_SYSTEM_SCHEDULE_NOT_FOUND.getDeveloperMessage()
            );

            error.getErros().add(erroBody);
        }
        //------------------//

        //ASSOCIATE HANDLERS//
        if (exception.getMessage().equals(SOUTH_SYSTEM_ASSOCIATE_NOT_FOUND.getMsgCorrelationId())) {
            var erroBody = new SouthSystemErro(
                    SOUTH_SYSTEM_ASSOCIATE_NOT_FOUND.getCode(), SOUTH_SYSTEM_ASSOCIATE_NOT_FOUND.getSequential(),
                    SOUTH_SYSTEM_ASSOCIATE_NOT_FOUND.getUserHelp(), SOUTH_SYSTEM_ASSOCIATE_NOT_FOUND.getMessage(),
                    SOUTH_SYSTEM_ASSOCIATE_NOT_FOUND.getMsgCorrelationId(), SOUTH_SYSTEM_ASSOCIATE_NOT_FOUND.getCategory(),
                    SOUTH_SYSTEM_ASSOCIATE_NOT_FOUND.getDeveloperMessage()
            );

            error.getErros().add(erroBody);
        }

        if (exception.getMessage().equals(SOUTH_SYSTEM_ASSOCIATE_CONFLICT.getMsgCorrelationId())) {
            var erroBody = new SouthSystemErro(
                    SOUTH_SYSTEM_ASSOCIATE_CONFLICT.getCode(), SOUTH_SYSTEM_ASSOCIATE_CONFLICT.getSequential(),
                    SOUTH_SYSTEM_ASSOCIATE_CONFLICT.getUserHelp(), SOUTH_SYSTEM_ASSOCIATE_CONFLICT.getMessage(),
                    SOUTH_SYSTEM_ASSOCIATE_CONFLICT.getMsgCorrelationId(), SOUTH_SYSTEM_ASSOCIATE_CONFLICT.getCategory(),
                    SOUTH_SYSTEM_ASSOCIATE_CONFLICT.getDeveloperMessage()
            );

            error.getErros().add(erroBody);
        }

        if (exception.getMessage().equals(SOUTH_SYSTEM_ASSOCIATE_UNABLE_TO_VOTE.getMsgCorrelationId())) {
            var erroBody = new SouthSystemErro(
                    SOUTH_SYSTEM_ASSOCIATE_UNABLE_TO_VOTE.getCode(), SOUTH_SYSTEM_ASSOCIATE_UNABLE_TO_VOTE.getSequential(),
                    SOUTH_SYSTEM_ASSOCIATE_UNABLE_TO_VOTE.getUserHelp(), SOUTH_SYSTEM_ASSOCIATE_UNABLE_TO_VOTE.getMessage(),
                    SOUTH_SYSTEM_ASSOCIATE_UNABLE_TO_VOTE.getMsgCorrelationId(), SOUTH_SYSTEM_ASSOCIATE_UNABLE_TO_VOTE.getCategory(),
                    SOUTH_SYSTEM_ASSOCIATE_UNABLE_TO_VOTE.getDeveloperMessage()
            );

            error.getErros().add(erroBody);
        }
        //------------------//

        //VOTE_SESSION HANDLERS//
        if (exception.getMessage().equals(SOUTH_SYSTEM_VOTE_SESSION_NOT_FOUND.getMsgCorrelationId())) {
            var erroBody = new SouthSystemErro(
                    SOUTH_SYSTEM_VOTE_SESSION_NOT_FOUND.getCode(), SOUTH_SYSTEM_VOTE_SESSION_NOT_FOUND.getSequential(),
                    SOUTH_SYSTEM_VOTE_SESSION_NOT_FOUND.getUserHelp(), SOUTH_SYSTEM_VOTE_SESSION_NOT_FOUND.getMessage(),
                    SOUTH_SYSTEM_VOTE_SESSION_NOT_FOUND.getMsgCorrelationId(), SOUTH_SYSTEM_VOTE_SESSION_NOT_FOUND.getCategory(),
                    SOUTH_SYSTEM_VOTE_SESSION_NOT_FOUND.getDeveloperMessage()
            );

            error.getErros().add(erroBody);
        }

        if (exception.getMessage().equals(SOUTH_SYSTEM_VOTE_SESSION_EXPIRED.getMsgCorrelationId())) {
            var erroBody = new SouthSystemErro(
                    SOUTH_SYSTEM_VOTE_SESSION_EXPIRED.getCode(), SOUTH_SYSTEM_VOTE_SESSION_EXPIRED.getSequential(),
                    SOUTH_SYSTEM_VOTE_SESSION_EXPIRED.getUserHelp(), SOUTH_SYSTEM_VOTE_SESSION_EXPIRED.getMessage(),
                    SOUTH_SYSTEM_VOTE_SESSION_EXPIRED.getMsgCorrelationId(), SOUTH_SYSTEM_VOTE_SESSION_EXPIRED.getCategory(),
                    SOUTH_SYSTEM_VOTE_SESSION_EXPIRED.getDeveloperMessage()
            );

            error.getErros().add(erroBody);
        }
        //------------------//

        if (exception.getMessage().equals(SOUTH_SYSTEM_VOTE_SESSION_BAD_CLOSING_DATE.getMsgCorrelationId())) {
            var erroBody = new SouthSystemErro(
                    SOUTH_SYSTEM_VOTE_SESSION_BAD_CLOSING_DATE.getCode(), SOUTH_SYSTEM_VOTE_SESSION_BAD_CLOSING_DATE.getSequential(),
                    SOUTH_SYSTEM_VOTE_SESSION_BAD_CLOSING_DATE.getUserHelp(), SOUTH_SYSTEM_VOTE_SESSION_BAD_CLOSING_DATE.getMessage(),
                    SOUTH_SYSTEM_VOTE_SESSION_BAD_CLOSING_DATE.getMsgCorrelationId(), SOUTH_SYSTEM_VOTE_SESSION_BAD_CLOSING_DATE.getCategory(),
                    SOUTH_SYSTEM_VOTE_SESSION_BAD_CLOSING_DATE.getDeveloperMessage()
            );

            error.getErros().add(erroBody);
        }

        if (exception.getMessage().equals(SOUTH_SYSTEM_HTTP_CLIENT_ERROR.getMsgCorrelationId())) {
            var erroBody = new SouthSystemErro(
                    SOUTH_SYSTEM_HTTP_CLIENT_ERROR.getCode(), SOUTH_SYSTEM_HTTP_CLIENT_ERROR.getSequential(),
                    SOUTH_SYSTEM_HTTP_CLIENT_ERROR.getUserHelp(), SOUTH_SYSTEM_HTTP_CLIENT_ERROR.getMessage(),
                    SOUTH_SYSTEM_HTTP_CLIENT_ERROR.getMsgCorrelationId(), SOUTH_SYSTEM_HTTP_CLIENT_ERROR.getCategory(),
                    SOUTH_SYSTEM_HTTP_CLIENT_ERROR.getDeveloperMessage()
            );

            error.getErros().add(erroBody);
        }

        return ResponseEntity.badRequest().body(error);
    }
}
