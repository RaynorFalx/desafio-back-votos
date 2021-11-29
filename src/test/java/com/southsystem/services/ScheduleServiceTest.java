package com.southsystem.services;

import com.southsystem.AbstractUnitTest;
import com.southsystem.dtos.request.schedule.CreateScheduleRequestImpl;
import com.southsystem.repositories.ScheduleRepository;
import com.southsystem.services.utils.MapService;
import com.southsystem.utils.exceptions.SouthSystemException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static com.southsystem.enums.FakeObjects.makeCreateScheduleRequestImpl;
import static com.southsystem.enums.FakeObjects.makeSchedule;
import static com.southsystem.enums.FakeObjects.makeDefaultRequestResponse;
import static com.southsystem.utils.exceptions.ExceptionMessages.SOUTH_SYSTEM_SCHEDULE_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static com.southsystem.utils.StatusCode.CREATED;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.any;


class ScheduleServiceTest extends AbstractUnitTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private MapService mapService;

    @InjectMocks
    private ScheduleService scheduleService;

    private CreateScheduleRequestImpl requestBody;

    @BeforeEach
    void setUp() {
        requestBody = makeCreateScheduleRequestImpl();
    }

    @Test
    @DisplayName("Teste de execução do método createSchedule")
    void testUm() {
        var schedule = makeSchedule();

        doReturn(schedule).when(scheduleRepository).save(any());
        when(mapService.mapDefaultCreateRequestResponse()).thenReturn(makeDefaultRequestResponse());

        var resultTest = scheduleService.createSchedule(requestBody);

        verify(scheduleRepository, times(1)).save(any());
        assertEquals(resultTest.getMessage(), CREATED.getdDescription());
        assertEquals(resultTest.getStatusCode(), CREATED.getCode());
    }

    @Test
    @DisplayName("Teste de execução do método findScheduleById")
    void testDois() {
        var schedule = Optional.of(makeSchedule());

       when(scheduleRepository.findById(1L)).thenReturn(schedule);

        var resultTest = scheduleService.findScheduleById(1L);

        verify(scheduleRepository, times(1)).findById(any());
        assertEquals(resultTest.getScheduleId(), schedule.get().getScheduleId());
        assertEquals(resultTest.getScheduleName(), schedule.get().getScheduleName());
        assertEquals(resultTest.getScheduleDescription(), schedule.get().getScheduleDescription());
    }

    @Test
    @DisplayName("Teste de execução do método findScheduleById lançando a exception de SCHEDULE_NOT_FOUND")
    void testTres() {

        when(scheduleRepository.findById(1L)).thenReturn(Optional.empty());

        var thrown  = assertThrows(SouthSystemException.class, () -> {
                    scheduleService.findScheduleById(1L);
        });

        verify(scheduleRepository, times(1)).findById(any());
        assertEquals(thrown.getMessage(), SOUTH_SYSTEM_SCHEDULE_NOT_FOUND.getMsgCorrelationId());

    }
}