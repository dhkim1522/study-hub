package com.springbasicapiserver.controller;

import com.springbasicapiserver.domain.Cert;
import com.springbasicapiserver.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cert")
public class ApiController {
    private final ApiService apiService;

    @GetMapping("/{id}")
    public Cert getCertById(@PathVariable Long id) {
        return apiService.findById(id);
    }
}
