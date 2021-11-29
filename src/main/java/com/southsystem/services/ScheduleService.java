package com.southsystem.services;

import com.southsystem.domain.Schedule;
import com.southsystem.dtos.request.schedule.CreateScheduleRequestImpl;
import com.southsystem.dtos.response.DefaultRequestResponseImpl;
import com.southsystem.repositories.ScheduleRepository;
import com.southsystem.services.utils.CalendarService;
import com.southsystem.services.utils.JsonService;
import com.southsystem.services.utils.MapService;
import com.southsystem.utils.exceptions.SouthSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.southsystem.utils.exceptions.ExceptionMessages.SOUTH_SYSTEM_SCHEDULE_NOT_FOUND;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private CalendarService calendarService;

    @Autowired
    private JsonService jsonService;

    @Autowired
    private MapService mapService;

    @Transactional
    public DefaultRequestResponseImpl createSchedule(CreateScheduleRequestImpl requestBody) {
        var newSchedule = new Schedule();

        newSchedule.setScheduleName(requestBody.getScheduleName());
        newSchedule.setScheduleDescription(requestBody.getScheduleDescription());

        scheduleRepository.save(newSchedule);
        return mapService.mapDefaultCreateRequestResponse();
    }

    public Schedule findScheduleById(Long scheduleId) {
        var schedule =  scheduleRepository.findById(scheduleId);

        if (schedule.isPresent()) {
            return schedule.get();
        } else {
            throw new SouthSystemException(SOUTH_SYSTEM_SCHEDULE_NOT_FOUND.getMsgCorrelationId());
        }
    }
}
