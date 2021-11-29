package com.southsystem.services;

import com.southsystem.AbstractUnitTest;
import com.southsystem.dtos.request.vote_session.OpenVoteSessionRequestImpl;
import com.southsystem.enums.FakeObjects;
import com.southsystem.repositories.ScheduleRepository;
import com.southsystem.repositories.VoteSessionRepository;
import com.southsystem.services.utils.CalendarService;
import com.southsystem.services.utils.MapService;
import com.southsystem.utils.exceptions.ExceptionMessages;
import com.southsystem.utils.exceptions.SouthSystemException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static com.southsystem.enums.FakeObjects.makeOpenVoteSessionRequestImpl;
import static com.southsystem.enums.FakeObjects.makeVoteSession;
import static com.southsystem.enums.FakeObjects.makeSchedule;
import static com.southsystem.enums.FakeObjects.makeDefaultRequestResponse;
import static com.southsystem.utils.StatusCode.CREATED;
import static com.southsystem.utils.exceptions.ExceptionMessages.SOUTH_SYSTEM_VOTE_SESSION_EXPIRED;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doReturn;
import static org.junit.jupiter.api.Assertions.assertEquals;

class VoteSessionServiceTest extends AbstractUnitTest {

    @Mock
    private VoteSessionRepository voteSessionRepository;

    @Mock
    private ScheduleService scheduleService;

    @Mock
    private CalendarService calendarService;

    @Mock
    private MapService mapService;

    @InjectMocks
    private VoteSessionService voteSessionService;

    private OpenVoteSessionRequestImpl requestBody;

    private final String dateNow =
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    @BeforeEach
    void setUp() {
        requestBody = makeOpenVoteSessionRequestImpl();
    }

    @Test
    @DisplayName("Teste de execução do método openVoteSession")
    void testUm() {
        var voteSession = makeVoteSession();
        var schedule = makeSchedule();
        var now = LocalDateTime.now();

        when(scheduleService.findScheduleById(requestBody.getScheduleId())).thenReturn(schedule);

        when(calendarService.parseFormatter(dateNow)).thenReturn(now);
        when(calendarService.parseFormatter(requestBody.getVoteSessionClosingDate().get())).thenReturn(now.plusDays(1));

        when(mapService.mapDefaultCreateRequestResponse()).thenReturn(makeDefaultRequestResponse());
        doReturn(voteSession).when(voteSessionRepository).save(any());

        var resultTest = voteSessionService.openVoteSession(requestBody);

        verify(voteSessionRepository, times(1)).save(any());
        assertEquals(resultTest.getMessage(), CREATED.getdDescription());
        assertEquals(resultTest.getStatusCode(), CREATED.getCode());

    }

    @Test
    @DisplayName("Teste de execução do método findVoteSesseionById")
    void testDois() {
        var voteSession = Optional.of(makeVoteSession());

        when(voteSessionRepository.findById(1L)).thenReturn(voteSession);

        var resultTest = voteSessionService.findVoteSesseionById(1L);

        assertEquals(resultTest.getVoteSessionId(), voteSession.get().getVoteSessionId());
        assertEquals(resultTest.getVoteSessionDescription(), voteSession.get().getVoteSessionDescription());
        assertEquals(resultTest.getVoteSessionCloseDate(), voteSession.get().getVoteSessionCloseDate());
        assertEquals(resultTest.getVoteSessionCloseDate(), voteSession.get().getVoteSessionCloseDate());
    }

    @Test
    @DisplayName("Teste de execução do método checkVoteSessionValidate lançando a exception de VOTE_SESSION_EXPIRED")
    void testTres() {
        var now = LocalDateTime.now();

        var voteSession = Optional.of(makeVoteSession());
        voteSession.get().setVoteSessionCloseDate(now.minusMinutes(1));

        when(voteSessionRepository.findById(1L)).thenReturn(voteSession);
        when(calendarService.parseFormatter(any())).thenReturn(now);

        var thrown = assertThrows(SouthSystemException.class, () -> {
            voteSessionService.checkVoteSessionValidate(1L);
        });

        assertEquals(thrown.getMessage(), SOUTH_SYSTEM_VOTE_SESSION_EXPIRED.getMsgCorrelationId());
    }


}