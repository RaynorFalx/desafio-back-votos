package com.southsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "vote")
public class Vote {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "VOTE_OPTION")
    private Character voteOption;

    //name = Nome que a coluna receberá, representando a FK da tabela de ligação
    //referencedColumnName = Coluna na tabela de ligação que representa o ON do JOIN
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "associateCpf", referencedColumnName = "CPF")
    private Associate associate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "voteSessionId", referencedColumnName = "ID")
    private VoteSession voteSession;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scheduleId", referencedColumnName = "ID")
    private Schedule schedule;

    public Vote() {
    }

    public Vote(Long id, Character voteOption, Associate associate, VoteSession voteSession) {
        this.id = id;
        this.voteOption = voteOption;
        this.associate = associate;
        this.voteSession = voteSession;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Character getVoteOption() {
        return voteOption;
    }

    public void setVoteOption(Character voteOption) {
        this.voteOption = voteOption;
    }

    public Associate getAssociate() {
        return associate;
    }

    public void setAssociate(Associate associate) {
        this.associate = associate;
    }

    public VoteSession getVoteSession() {
        return voteSession;
    }

    public void setVoteSession(VoteSession voteSession) {
        this.voteSession = voteSession;
    }
}
