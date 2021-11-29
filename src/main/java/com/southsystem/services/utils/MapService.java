package com.southsystem.services.utils;

import com.southsystem.dtos.response.DefaultRequestResponseImpl;
import org.springframework.stereotype.Service;

import static com.southsystem.utils.StatusCode.CREATED;

@Service
public class MapService {

    public DefaultRequestResponseImpl mapDefaultCreateRequestResponse() {
        var response = new DefaultRequestResponseImpl();

        response.setMessage(CREATED.getdDescription());
        response.setStatusCode(CREATED.getCode());

        return response;
    }
}
