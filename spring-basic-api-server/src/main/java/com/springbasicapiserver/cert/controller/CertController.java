package com.springbasicapiserver.cert.controller;

import com.springbasicapiserver.cert.domain.Cert;
import com.springbasicapiserver.cert.service.CertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cert")
public class CertController {
    private final CertService certService;

    @GetMapping("/{id}")
    public Cert getCertById(@PathVariable Long id) {
        return certService.findById(id);
    }
}
