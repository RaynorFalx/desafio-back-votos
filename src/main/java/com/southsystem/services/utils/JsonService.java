package com.southsystem.services.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.southsystem.utils.exceptions.SouthSystemException;
import org.springframework.stereotype.Service;

@Service
public class JsonService {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    public String stringify(Object obj) throws SouthSystemException {
        try {
            return JSON_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new SouthSystemException("Erro ao tentar converter POJO's para JSON's");
        }
    }

    public <T> T parse(String value, Class<T> clazz) throws SouthSystemException {
        try {
            return JSON_MAPPER.readValue(value, clazz);
        } catch (JsonProcessingException e) {
            throw new SouthSystemException("Erro ao tentar converter JSON's para POJO's");
        }
    }
}
