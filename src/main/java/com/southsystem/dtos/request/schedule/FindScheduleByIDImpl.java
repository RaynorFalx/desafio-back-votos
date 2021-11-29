package com.southsystem.dtos.request.schedule;

public class FindScheduleByIDImpl implements FindScheduleByIDRequest {

    private Long scheduleId;

    @Override
    public Long getScheduleId() {
        return scheduleId;
    }
}
