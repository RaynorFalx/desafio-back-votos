package com.southsystem.dtos.request.schedule;

import com.southsystem.resources.validator.schedule.CreateScheduleValidator;
import io.swagger.annotations.ApiModelProperty;

@CreateScheduleValidator
public class CreateScheduleRequestImpl implements CreateScheduleRequest {


    private String scheduleName;
    private String scheduleDescription;

    @Override
    public String getScheduleName() {
        return scheduleName;
    }

    @Override
    public String getScheduleDescription() {
        return scheduleDescription;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public void setScheduleDescription(String scheduleDescription) {
        this.scheduleDescription = scheduleDescription;
    }
}
