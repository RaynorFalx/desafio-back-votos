package com.southsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "associate")
public class Associate {

    @ApiModelProperty(
            name = "CPF identificador do Associdado",
            notes = "cpf.",
            example = "73403148149"
    )
    @Id
    @Column(name = "CPF")
    private Long cpf;

    @ApiModelProperty(
            name = "Nome identificador do Associdado",
            notes = "associateName.",
            example = "Juliano Afonso Dias Rodrigues"
    )
    @Column(name = "NAME")
    private String associateName;

    @JsonIgnore
    @OneToMany(mappedBy = "associate", fetch = FetchType.LAZY)
    private Set<Vote> votes;

    public Associate() {
    }

    public Associate(Long cpf, String associateName, Set<Vote> votes) {
        this.cpf = cpf;
        this.associateName = associateName;
        this.votes = votes;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getAssociateName() {
        return associateName;
    }

    public void setAssociateName(String associateName) {
        this.associateName = associateName;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }
}
