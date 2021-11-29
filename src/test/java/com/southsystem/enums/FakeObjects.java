package com.southsystem.enums;

import com.southsystem.domain.Associate;
import com.southsystem.domain.Schedule;
import com.southsystem.domain.VoteSession;
import com.southsystem.dtos.request.associate.AssingAssociateRequestImpl;
import com.southsystem.dtos.request.schedule.CreateScheduleRequestImpl;
import com.southsystem.dtos.request.vote_session.OpenVoteSessionRequestImpl;
import com.southsystem.dtos.response.DefaultRequestResponseImpl;

import java.time.LocalDateTime;

import static com.southsystem.utils.StatusCode.CREATED;

public enum FakeObjects {
    ;

    private static final Long cpf = 12345678901L;
    private static final String name = "Entidade de Testes";

    public static Associate makeAssociate() {
        var associate = new Associate();

        associate.setCpf(cpf);
        associate.setAssociateName(name);

        return associate;
    }

    public static AssingAssociateRequestImpl makeAssingAssociateRequestImpl() {
        var requestBody = new AssingAssociateRequestImpl();

        requestBody.setAssociateCpf(cpf);
        requestBody.setAssociateName(name);

        return requestBody;
    }

    public static Schedule makeSchedule() {
        var schedule = new Schedule();

        schedule.setScheduleId(1L);
        schedule.setScheduleName("Pauta");
        schedule.setScheduleDescription("Pauta de Teste");

        return schedule;
    }

    public static CreateScheduleRequestImpl makeCreateScheduleRequestImpl() {
        var requestBody = new CreateScheduleRequestImpl();

        requestBody.setScheduleName("Pauta");
        requestBody.setScheduleDescription("Pauta de Teste");

        return requestBody;
    }

    public static DefaultRequestResponseImpl makeDefaultRequestResponse() {
        var response = new DefaultRequestResponseImpl();

        response.setMessage(CREATED.getdDescription());
        response.setStatusCode(CREATED.getCode());

        return response;
    }

    public static VoteSession makeVoteSession() {
        var voteSession = new VoteSession();

        voteSession.setVoteSessionId(1L);
        voteSession.setVoteSessionDescription("Seção de Votação");
        voteSession.setVoteSessionAssingDate(LocalDateTime.now());
        voteSession.setVoteSessionCloseDate(LocalDateTime.now().plusMinutes(1));

        return voteSession;
    }

    public static OpenVoteSessionRequestImpl makeOpenVoteSessionRequestImpl() {
        var requestBody = new OpenVoteSessionRequestImpl();

        requestBody.setScheduleId(1L);
        requestBody.setVoteSessionDescription("Seção de Votação");
        requestBody.setVoteSessionClosingDate(LocalDateTime.now().plusDays(1).toString());

        return requestBody;
    }
}
