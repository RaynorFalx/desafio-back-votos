package com.southsystem.dtos.request.vote_session;

import com.southsystem.resources.validator.vote_session.OpenVoteSessionValidator;

import java.util.Optional;

@OpenVoteSessionValidator
public class OpenVoteSessionRequestImpl implements OpenVoteSessionRequest {

    private Long scheduleId;
    private String voteSessionDescription;
    private String voteSessionClosingDate;

    public OpenVoteSessionRequestImpl() {
    }

    @Override
    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Override
    public String getVoteSessionDescription() {
        return voteSessionDescription;
    }

    public void setVoteSessionDescription(String voteSessionDescription) {
        this.voteSessionDescription = voteSessionDescription;
    }

    public void setVoteSessionClosingDate(String voteSessionClosingDate) {
        this.voteSessionClosingDate = voteSessionClosingDate;
    }

    @Override
    public Optional<String> getVoteSessionClosingDate() {
        return voteSessionClosingDate == null
                ? Optional.empty()
                : Optional.of(voteSessionClosingDate);
    }

}


