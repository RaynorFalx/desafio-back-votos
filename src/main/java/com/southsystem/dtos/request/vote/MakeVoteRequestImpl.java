package com.southsystem.dtos.request.vote;

import com.southsystem.resources.validator.vote.MakeVoteValidator;
import io.swagger.annotations.ApiModelProperty;

@MakeVoteValidator
public class MakeVoteRequestImpl implements MakeVoteRequest {

    private Long voteSessionId;
    private Long associateCpf;
    private Character vote;

    public MakeVoteRequestImpl() {
    }

    public MakeVoteRequestImpl(Long voteSessionId, Long associateCpf, Character vote) {
        this.voteSessionId = voteSessionId;
        this.associateCpf = associateCpf;
        this.vote = vote;
    }

    @Override
    public Long getVoteSessionId() {
        return voteSessionId;
    }

    public void setVoteSessionId(Long voteSessionId) {
        this.voteSessionId = voteSessionId;
    }

    @Override
    public Long getAssociateCpf() {
        return associateCpf;
    }

    public void setAssociateCpf(Long associateCpf) {
        this.associateCpf = associateCpf;
    }

    @Override
    public Character getVote() {
        return vote;
    }

    public void setVote(Character vote) {
        this.vote = vote;
    }
}
