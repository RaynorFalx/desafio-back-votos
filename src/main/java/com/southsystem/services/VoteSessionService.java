package com.southsystem.services;

import com.southsystem.domain.VoteSession;
import com.southsystem.dtos.request.vote_session.OpenVoteSessionRequestImpl;
import com.southsystem.dtos.response.DefaultRequestResponseImpl;
import com.southsystem.repositories.VoteSessionRepository;
import com.southsystem.services.utils.CalendarService;
import com.southsystem.services.utils.MapService;
import com.southsystem.utils.exceptions.SouthSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.southsystem.utils.exceptions.ExceptionMessages.*;
import static com.southsystem.utils.exceptions.ExceptionMessages.SOUTH_SYSTEM_VOTE_SESSION_EXPIRED;
import static com.southsystem.utils.exceptions.ExceptionMessages.SOUTH_SYSTEM_VOTE_SESSION_NOT_FOUND;

@Service
public class VoteSessionService {

    @Autowired
    private VoteSessionRepository voteSessionRepository;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private MapService mapService;

    @Autowired
    private CalendarService calendarService;

    private final String dateNow =
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    public DefaultRequestResponseImpl openVoteSession(OpenVoteSessionRequestImpl requestBody) {

        //Check de Pauta enviada no body//
        var schedule = scheduleService.findScheduleById(requestBody.getScheduleId());
        //------------------------------//

        var voteSession = new VoteSession();

        voteSession.setSchedule(schedule);
        voteSession.setVoteSessionDescription(requestBody.getVoteSessionDescription());
        voteSession.setVoteSessionAssingDate(calendarService.parseFormatter(dateNow));

        if (requestBody.getVoteSessionClosingDate().isPresent()) {
            voteSession.setVoteSessionCloseDate((
                    calendarService.parseFormatter(requestBody.getVoteSessionClosingDate().get())
            ));
        } else {
            voteSession.setVoteSessionCloseDate(calendarService.parseFormatter(dateNow).plusMinutes(1));
        }

        voteSessionRepository.save(voteSession);
        return mapService.mapDefaultCreateRequestResponse();
    }

    public VoteSession findVoteSesseionById(Long voteSessionId) {
        var voteSession = voteSessionRepository.findById(voteSessionId);

        if (voteSession.isPresent()) {
            return voteSession.get();
        } else {
            throw new SouthSystemException(SOUTH_SYSTEM_VOTE_SESSION_NOT_FOUND.getMsgCorrelationId());
        }
    }

    public void checkVoteSessionValidate(Long voteSessionId) {
        var voteSession = this.findVoteSesseionById(voteSessionId);
        var now = calendarService.parseFormatter(dateNow);

        if (now.isAfter(voteSession.getVoteSessionCloseDate())) {
            throw new SouthSystemException(SOUTH_SYSTEM_VOTE_SESSION_EXPIRED.getMsgCorrelationId());
        }
    }

    public void checkVotesOfAssociates(Long associateCpf, Long voteSessionId) {
        var voteSession = findVoteSesseionById(voteSessionId);
        var votes = voteSession.getVotes();

        var stream = votes.stream().filter(
                vote -> Objects.equals(vote.getAssociate().getCpf(), associateCpf)).collect(Collectors.toList()
        );

        if (stream.size() != 0) {
            throw new SouthSystemException(SOUTH_SYSTEM_VOTE_SESSION_ALREADY_VOTED.getMsgCorrelationId());
        }
    }

}
