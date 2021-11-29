package com.southsystem.resources;

import com.southsystem.AbstractMvcTest;
import com.southsystem.enums.FakeObjects;
import com.southsystem.repositories.AssociateRepository;
import com.southsystem.services.AssociateService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.southsystem.enums.FakeObjects.makeAssingAssociateRequestImpl;
import static com.southsystem.enums.FakeObjects.makeDefaultRequestResponse;
import static com.southsystem.enums.FakeObjects.makeAssociate;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AssociateResourceTest extends AbstractMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AssociateService associateService;

    @Mock
    private AssociateRepository associateRepository;

    @Test
    @DisplayName("Execução do end point /assingAssociate")
    void testUm() throws Exception {
        var requestBody = makeAssingAssociateRequestImpl();
        var associate = makeAssociate();

        var response = makeDefaultRequestResponse();

        when(associateService.assingAssociate(requestBody)).thenReturn(response);
        doReturn(associate).when(associateRepository).save(any());

        var request = post("/assingAssociate")
                .content("{\"associateCpf\":12345678901,\"associateName\":\"Entidade de Teste\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statusCode", is("201")))
                .andExpect(jsonPath("$.message", is("Um novo recurso foi criado com sucesso.")));
    }
}
