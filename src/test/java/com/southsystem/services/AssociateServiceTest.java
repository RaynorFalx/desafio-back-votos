package com.southsystem.services;

import com.southsystem.AbstractUnitTest;
import com.southsystem.dtos.request.associate.AssingAssociateRequestImpl;
import com.southsystem.enums.FakeObjects;
import com.southsystem.repositories.AssociateRepository;
import com.southsystem.services.utils.MapService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static com.southsystem.enums.FakeObjects.makeAssingAssociateRequestImpl;
import static com.southsystem.enums.FakeObjects.makeAssociate;
import static com.southsystem.enums.FakeObjects.makeDefaultRequestResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AssociateServiceTest extends AbstractUnitTest {

    @Mock
    private AssociateRepository associateRepository;

    @Mock
    private MapService mapService;

    @InjectMocks
    private AssociateService associateService;

    private AssingAssociateRequestImpl requestBody;

    @BeforeEach
    void setUp() {
        requestBody = makeAssingAssociateRequestImpl();
    }

    @Test
    @DisplayName("Teste de execução do método assingAssociate")
    void testUm() {
        var associate = Optional.of(makeAssociate());

        when(associateRepository.findById(requestBody.getAssociateCpf())).thenReturn(Optional.empty());
        when(mapService.mapDefaultCreateRequestResponse()).thenReturn(makeDefaultRequestResponse());
        doReturn(associate.get()).when(associateRepository).save(any());

        var resultTest = associateService.assingAssociate(requestBody);

        verify(associateRepository, times(1)).save(any());

        assertEquals(resultTest.getMessage(), "Um novo recurso foi criado com sucesso.");
        assertEquals(resultTest.getStatusCode(), "201");
    }

    @Test
    @DisplayName("Teste de execução do método findAssociateByCPF")
    void TestDois() {
        var associate = Optional.of(makeAssociate());

        when(associateRepository.findById(requestBody.getAssociateCpf())).thenReturn(associate);

        var resultTest = associateService.findAssociateByCPF(requestBody.getAssociateCpf());

        assertEquals(resultTest.getCpf(), associate.get().getCpf());
        assertEquals(resultTest.getAssociateName(), associate.get().getAssociateName());
    }
}