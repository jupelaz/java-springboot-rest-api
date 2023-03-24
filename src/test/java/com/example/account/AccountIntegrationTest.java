package com.example.account;

import com.example.account.model.dto.AccountRequest;
import com.example.account.model.dto.AccountResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
@SpringBootTest
@AutoConfigureMockMvc
public class AccountIntegrationTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final String POST_PATH = "/api/account";
    @Test
    public void givenAValidAccountRequest_whenPost_thenASuccessResponseIsReturned() throws Exception {
        var accountRequest = AccountRequest.builder().firstName("John").lastName("Doe")
                .balance(2.0)
                .build();

        var response = mockMvc.perform(
                        post(POST_PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(accountRequest)))
                .andReturn().getResponse();

        AccountResponse accountResponse = objectMapper
                .readValue(response.getContentAsString(), AccountResponse.class);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(2.0, accountResponse.getBalance());
    }

    @Test
    public void givenAnInvalidDispenserRequest_whenPost_thenAnErrorResponseIsReturned() throws Exception {
        var dispenserRequest = AccountRequest.builder().balance(-1.1).build();

        var response = mockMvc.perform(
                        post(POST_PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dispenserRequest)))
                .andReturn().getResponse();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
    }
}

