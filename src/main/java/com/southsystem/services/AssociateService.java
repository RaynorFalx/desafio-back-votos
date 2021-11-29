package com.southsystem.services;

import com.southsystem.domain.Associate;
import com.southsystem.dtos.request.associate.AssingAssociateRequestImpl;
import com.southsystem.dtos.response.DefaultRequestResponseImpl;
import com.southsystem.repositories.AssociateRepository;
import com.southsystem.services.utils.JsonService;
import com.southsystem.services.utils.MapService;
import com.southsystem.utils.exceptions.SouthSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

import java.util.Objects;

import static com.southsystem.utils.exceptions.ExceptionMessages.SOUTH_SYSTEM_ASSOCIATE_NOT_FOUND;
import static com.southsystem.utils.exceptions.ExceptionMessages.SOUTH_SYSTEM_ASSOCIATE_CONFLICT;
import static com.southsystem.utils.exceptions.ExceptionMessages.SOUTH_SYSTEM_ASSOCIATE_UNABLE_TO_VOTE;
import static com.southsystem.utils.exceptions.ExceptionMessages.SOUTH_SYSTEM_HTTP_CLIENT_ERROR;

@Service
public class AssociateService {

    @Autowired
    private AssociateRepository associateRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JsonService jsonService;

    @Value("${cpf.api}")
    private String cpfApi;

    @Autowired
    private MapService mapService;

    @Transactional
    public DefaultRequestResponseImpl assingAssociate(AssingAssociateRequestImpl requestBody) {
        //Check de Associado já existente//
        checkAssociateExistence(requestBody.getAssociateCpf());
        //------------------------------//

        var associtate = new Associate();

        associtate.setAssociateName(requestBody.getAssociateName());
        associtate.setCpf(requestBody.getAssociateCpf());

        associateRepository.save(associtate);

        return mapService.mapDefaultCreateRequestResponse();
    }

    public Associate findAssociateByCPF(Long cpf) {
        var associate = associateRepository.findById(cpf);

        if (associate.isPresent()) {
            return associate.get();
        } else {
            throw new SouthSystemException(
                    SOUTH_SYSTEM_ASSOCIATE_NOT_FOUND.getMsgCorrelationId()
            );
        }
    }

    public void checkValidCpf(Long associateCpf) {
        try {
            var URI = cpfApi.concat("/" + String.valueOf(associateCpf));

            System.out.println("URI: ".concat(cpfApi).concat("/" + String.valueOf(associateCpf)));

            final var resultCall =
                    restTemplate.getForEntity(URI, String.class, String.valueOf(associateCpf));

            System.out.println("RESULT CALL: " + resultCall);

            if (Objects.equals(resultCall.getBody(), "{\"status\":\"UNABLE_TO_VOTE\"}")) {
                throw new SouthSystemException(SOUTH_SYSTEM_ASSOCIATE_UNABLE_TO_VOTE.getMsgCorrelationId());
            }
        } catch (HttpClientErrorException exception) {
            throw new SouthSystemException(SOUTH_SYSTEM_HTTP_CLIENT_ERROR.getMsgCorrelationId());
        }

    }

    private void checkAssociateExistence(Long cpf) {
        var associate = associateRepository.findById(cpf);

        if (associate.isPresent()) {
            throw new SouthSystemException(
                    SOUTH_SYSTEM_ASSOCIATE_CONFLICT.getMsgCorrelationId()
            );
        }
    }
}
