package com.southsystem.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "voteSession")
public class VoteSession {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteSessionId;

    @Column(name = "DESCRIPTION")
    private String voteSessionDescription;

    @Column(name = "DURATION_TIME")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime voteSessionAssingDate;

    @Column(name = "CLOSE_DATE")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime voteSessionCloseDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "scheduleId", referencedColumnName = "ID")
    private Schedule schedule;

    @OneToMany(mappedBy = "voteSession", fetch = FetchType.EAGER)
    private Set<Vote> votes;

    public VoteSession() {
    }

    public VoteSession(Long voteSessionId, String voteSessionDescription, LocalDateTime voteSessionAssingDate, LocalDateTime voteSessionCloseDate, Schedule schedule, Set<Vote> votes) {
        this.voteSessionId = voteSessionId;
        this.voteSessionDescription = voteSessionDescription;
        this.voteSessionAssingDate = voteSessionAssingDate;
        this.voteSessionCloseDate = voteSessionCloseDate;
        this.schedule = schedule;
        this.votes = votes;
    }

    public Long getVoteSessionId() {
        return voteSessionId;
    }

    public void setVoteSessionId(Long voteSessionId) {
        this.voteSessionId = voteSessionId;
    }

    public String getVoteSessionDescription() {
        return voteSessionDescription;
    }

    public void setVoteSessionDescription(String voteSessionDescription) {
        this.voteSessionDescription = voteSessionDescription;
    }

    public LocalDateTime getVoteSessionAssingDate() {
        return voteSessionAssingDate;
    }

    public void setVoteSessionAssingDate(LocalDateTime voteSessionAssingDate) {
        this.voteSessionAssingDate = voteSessionAssingDate;
    }

    public LocalDateTime getVoteSessionCloseDate() {
        return voteSessionCloseDate;
    }

    public void setVoteSessionCloseDate(LocalDateTime voteSessionCloseDate) {
        this.voteSessionCloseDate = voteSessionCloseDate;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }
}
