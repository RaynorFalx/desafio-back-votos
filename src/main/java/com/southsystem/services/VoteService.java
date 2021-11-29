package com.southsystem.services;

import com.southsystem.domain.Vote;
import com.southsystem.dtos.request.vote.MakeVoteRequestImpl;
import com.southsystem.dtos.response.DefaultRequestResponseImpl;
import com.southsystem.repositories.VoteRepository;
import com.southsystem.services.utils.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    @Autowired
    private AssociateService associateService;

    @Autowired
    private VoteSessionService voteSessionService;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private MapService mapService;

    public DefaultRequestResponseImpl makeVote(MakeVoteRequestImpl requestBody) {

        //Check validade CPF//
        associateService.checkValidCpf(requestBody.getAssociateCpf());
        //-----------------//

        //Check validade Seção de Votação//
        voteSessionService.checkVoteSessionValidate(requestBody.getVoteSessionId());
        //-----------------//

        //Check votos repetidos do Associado//
        voteSessionService.checkVotesOfAssociates(requestBody.getAssociateCpf(), requestBody.getVoteSessionId());
        //-----------------//

        var voteSession = voteSessionService.findVoteSesseionById(requestBody.getVoteSessionId());
        var associate = associateService.findAssociateByCPF(requestBody.getAssociateCpf());
        var vote = new Vote();

        vote.setAssociate(associate);
        vote.setVoteSession(voteSession);
        vote.setVoteOption(requestBody.getVote().toString().toUpperCase().charAt(0));

        voteRepository.save(vote);

        return mapService.mapDefaultCreateRequestResponse();
    }
}
