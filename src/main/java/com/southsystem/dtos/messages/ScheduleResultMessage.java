package com.southsystem.dtos.messages;

import java.io.Serializable;
import java.util.UUID;

public class ScheduleResultMessage implements Serializable {

    static final long serialVersionUID = -7222758960344325679L;

    private UUID uuid;
    private String message;

    public ScheduleResultMessage() {
    }

    public ScheduleResultMessage(UUID uuid, String message) {
        this.uuid = uuid;
        this.message = message;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
