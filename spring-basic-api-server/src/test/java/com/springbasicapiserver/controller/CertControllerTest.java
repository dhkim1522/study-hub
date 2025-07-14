package com.springbasicapiserver.controller;

import com.springbasicapiserver.domain.Cert;
import com.springbasicapiserver.error.ErrorCode;
import com.springbasicapiserver.error.GlobalExceptionHandler;
import com.springbasicapiserver.error.exception.CertNotFoundException;
import com.springbasicapiserver.service.CertService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CertController.class)
@Import({GlobalExceptionHandler.class, CertControllerTest.TestConfig.class})
class CertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CertService certService;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public CertService apiService() {
            return mock(CertService.class);
        }
    }

    @Test
    @DisplayName("getCertById: 존재하는 Cert ID로 조회하면 200 OK와 Cert를 반환한다")
    void getCertById_success() throws Exception {
        Cert cert = new Cert(1L, "test-cert", "CN=test");
        given(certService.findById(1L)).willReturn(cert);

        mockMvc.perform(get("/api/cert/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cert.id()))
                .andExpect(jsonPath("$.name").value(cert.name()))
                .andExpect(jsonPath("$.subject").value(cert.subject()));
    }

    @Test
    @DisplayName("getCertById: 존재하지 않는 Cert ID로 조회하면 404 NOT_FOUND 와 에러 메시지를 반환한다")
    void getCertById_notFound() throws Exception {
        given(certService.findById(999L)).willThrow(new CertNotFoundException());

        mockMvc.perform(get("/api/cert/{id}", 999L))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(ErrorCode.CERT_NOT_FOUND.name()))
                .andExpect(jsonPath("$.message").value(ErrorCode.CERT_NOT_FOUND.getMessage()));
    }
}
