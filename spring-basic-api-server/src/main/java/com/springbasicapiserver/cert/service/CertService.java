package com.springbasicapiserver.cert.service;

import com.springbasicapiserver.cert.domain.Cert;
import com.springbasicapiserver.common.error.exception.CertNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertService {

    private final List<Cert> certStore = List.of(
            new Cert(1L, "cert1", "CN=cert1"),
            new Cert(2L, "cert2", "CN=cert2"),
            new Cert(3L, "cert3", "CN=cert3")
    );

    public Cert findById(Long id) {
        return certStore.stream()
                .filter(cert -> cert.id().equals(id))
                .findFirst()
                .orElseThrow(CertNotFoundException::new);
    }
}
