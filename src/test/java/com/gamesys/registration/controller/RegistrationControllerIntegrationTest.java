package com.gamesys.registration.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamesys.registration.domain.RegistrationRequest;
import com.gamesys.registration.service.RegistrationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import java.time.LocalDate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
@DisplayName("Registration Controller Integration Test")
public class RegistrationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private RegistrationService service;


    @Test
    @DisplayName("Register user successfully when valid input is provided")
    void registerUserCorrectly() throws Exception {
        RegistrationRequest request = new RegistrationRequest("ReeteshABC", "Kumar12", LocalDate.of(1990, 01, 03), "5000345678345672");

        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Reject registration when invalid username is provided")
    public void should_reject_invalid_username() throws Exception {
        RegistrationRequest request = new RegistrationRequest("Reetesh128@", "ABCd1456", LocalDate.of(2010, 04, 01), "145678900123456");
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Reject registration when invalid password is provided")
    public void should_reject_invalid_password() throws Exception {
        RegistrationRequest request = new RegistrationRequest("Reetesh128", "ABC", LocalDate.of(2010, 04, 01), "145678900123456");
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Reject registration when invalid payment card number is provided")
    public void should_reject_invalid_payment_card_number() throws Exception {
        RegistrationRequest request = new RegistrationRequest("ReeteshABC", "Kumar12", LocalDate.of(1990, 03, 01), "500034567834");
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Reject registration when username is blank")
    public void should_reject_blank_username() throws Exception {
        RegistrationRequest request = new RegistrationRequest(" ", "Kumar12", LocalDate.of(1990, 03, 01), "500034567834");
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Reject registration when username is NULL")
    public void should_reject_NULL_username() throws Exception {
        RegistrationRequest request = new RegistrationRequest(null, "Kumar12", LocalDate.of(1990, 03, 01), "500034567834");
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Reject registration when password is blank")
    public void should_reject_blank_password() throws Exception {
        RegistrationRequest request = new RegistrationRequest("ReeteshKPT", " ", LocalDate.of(1990, 03, 01), "500034567834");
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Reject registration when password is NULL")
    public void should_reject_NULL_password() throws Exception {
        RegistrationRequest request = new RegistrationRequest("ReeteshKPT", null, LocalDate.of(1990, 03, 01), "500034567834");
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Reject registration when dob is blank")
    public void should_reject_blank_dob() throws Exception {
        RegistrationRequest request = new RegistrationRequest("ReeteshKPT", "ABCd1456", LocalDate.of(1990, 03, 01), "");
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Reject registration when dob is NULL")
    public void should_reject_NULL_dob() throws Exception {
        RegistrationRequest request = new RegistrationRequest("ReeteshKPT", "ABCd1456", LocalDate.of(1990, 03, 01), null);
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }


    @Test
    @DisplayName("Reject registration when payment card number is NULL")
    public void should_reject_NULL_PaymentCardNumber() throws Exception {
        RegistrationRequest request = new RegistrationRequest("ReeteshKPT", "ABCd1456", null, "500034567834");
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Reject registration when username is already registered")
    public void should_return_forbidden_when_user_is_already_registered() throws Exception {
        RegistrationRequest request = new RegistrationRequest("Reetesh", "ABCd1456", LocalDate.of(1983, 04, 01), "1100098900123456");
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("Reject registration when  user card number is blacklisted")
    public void should_return_Conflict_when_user_card_number_in_black_list() throws Exception {
        RegistrationRequest request = new RegistrationRequest("Reetesh128", "ABCd1456", LocalDate.of(1983, 04, 01), "1000098900123456");
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    @DisplayName("Reject registration when user age is less than 18")
    public void should_return_forbidden_when_user_age_is_invalid() throws Exception {
        RegistrationRequest request = new RegistrationRequest("Reetesh128", "ABCd1456", LocalDate.of(2010, 04, 01), "145678900123456");
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }


}