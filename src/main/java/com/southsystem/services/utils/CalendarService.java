package com.southsystem.services.utils;

import com.southsystem.utils.exceptions.ExceptionMessages;
import com.southsystem.utils.exceptions.SouthSystemException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import static com.southsystem.utils.exceptions.ExceptionMessages.*;

@Service
public class CalendarService {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LocalDateTime parseFormatter(String dateString) {
        try {
            return LocalDateTime.parse(dateString, formatter);
        } catch (DateTimeException exception) {
            throw new SouthSystemException(SOUTH_SYSTEM_VOTE_SESSION_BAD_CLOSING_DATE.getMsgCorrelationId());
        }
    }


}
