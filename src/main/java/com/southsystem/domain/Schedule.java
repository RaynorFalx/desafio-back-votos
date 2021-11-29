package com.southsystem.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "schedule")
public class Schedule {

    @ApiModelProperty(
            name = "ID identificador da Pauta",
            notes = "id.",
            example = "1"
    )
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @ApiModelProperty(
            name = "Nome identificador da Pauta",
            notes = "scheduleName.",
            example = "Pauta Emergêncial"
    )
    @Column(name = "NAME")
    private String scheduleName;

    @ApiModelProperty(
            name = "Descrição da Pauta",
            notes = "scheduleDescription.",
            example = "Pauta para tratar do pagamento de horas extras."
    )
    @Column(name = "DESCRIPTION")
    private String scheduleDescription;

    @ApiModelProperty(
            name = "Seção de Votação da Pauta",
            notes = "voteSessions."
    )
    @OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY)
    private Set<VoteSession> voteSessions;

    @ApiModelProperty(
            name = "Votos da Pauta da Pauta",
            notes = "votes."
    )
    @OneToMany(mappedBy = "schedule", fetch = FetchType.EAGER)
    private Set<Vote> votes;

    public Schedule() {
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public String getScheduleDescription() {
        return scheduleDescription;
    }

    public void setScheduleDescription(String scheduleDescription) {
        this.scheduleDescription = scheduleDescription;
    }
}
