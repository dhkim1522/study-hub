package dhkim.javaspringtest;

import dhkim.javaspringtest.entity.*;
import dhkim.javaspringtest.repository.CaCertRepository;
import dhkim.javaspringtest.repository.LeafCertRepository;
import dhkim.javaspringtest.repository.PolicyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class JavaSpringTestApplicationTests {

    @Autowired
    private CaCertRepository caCertRepository;
    @Autowired
    private LeafCertRepository leafCertRepository;
    @Autowired
    private PolicyRepository policyRepository;

    @BeforeEach
    void setUp() {
        // 1. 데이터 생성
        // Root CA
        CaCertEntity rootCa = CaCertEntity.builder()
                .cert("root-cert-data")
                .serial("serial-root")
                .subjectDn("cn=root.example.com")
                .notBefore(LocalDateTime.now())
                .notAfter(LocalDateTime.now().plusYears(20))
                .lastIssued(true)
                .valid(true)
                .keyAlgo("RSA")
                .keySpec("2048")
                .signAlgo("SHA256withRSA")
                .moId("rootCa")
                .akId(true)
                .skId(true)
                .pathLength(new PathLength(1))
                .providerId(null)
                .wcaId("wca1")
                .build();

        // Sub CA 1
        CaCertEntity subCa = CaCertEntity.builder()
                .cert("sub-cert-data")
                .serial("serial-sub1")
                .subjectDn("cn=sub1.example.com")
                .notBefore(LocalDateTime.now())
                .notAfter(LocalDateTime.now().plusYears(10))
                .lastIssued(true)
                .valid(true)
                .keyAlgo("RSA")
                .keySpec("2048")
                .signAlgo("SHA256withRSA")
                .moId("subCa")
                .akId(true)
                .skId(true)
                .pathLength(new PathLength(1))
                .providerId(null)
                .wcaId("wca1")
                .issuer(rootCa)
                .build();

        // Sub CA 2
        CaCertEntity subCa2 = CaCertEntity.builder()
                .cert("sub-cert-data")
                .serial("serial-sub2")
                .subjectDn("cn=sub2.example.com")
                .notBefore(LocalDateTime.now())
                .notAfter(LocalDateTime.now().plusYears(10))
                .lastIssued(true)
                .valid(true)
                .keyAlgo("RSA")
                .keySpec("2048")
                .signAlgo("SHA256withRSA")
                .moId("subCa")
                .akId(true)
                .skId(true)
                .pathLength(new PathLength(0))
                .providerId("M11")
                .wcaId("wca1")
                .issuer(subCa)
                .build();

        // Leaf Cert
        LeafCertEntity leafCert = LeafCertEntity.builder()
                .cert("leaf-cert-data")
                .serial("serial-leaf")
                .subjectDn("cn=leaf.example.com")
                .notBefore(LocalDateTime.now())
                .notAfter(LocalDateTime.now().plusYears(1))
                .lastIssued(true)
                .valid(true)
                .keyAlgo("RSA")
                .keySpec("2048")
                .signAlgo("SHA256withRSA")
                .issuer(subCa2)
                .cn("leaf.example.com")
                .pfxCert("pfx-cert-data")
                .build();

        // Policy
        PolicyEntity policy = PolicyEntity.builder()
                .caCert(subCa2)
                .name("Test Policy")
                .providerId("M11")
                .build();

        caCertRepository.save(rootCa);
        caCertRepository.save(subCa);
        caCertRepository.save(subCa2);
        leafCertRepository.save(leafCert);
        policyRepository.save(policy);
    }

    @Test
    void findAll() {
        List<CaCertEntity> all = caCertRepository.findAll();

        System.out.println("All CA Certs: " + all.size());
    }

    @Test
    void findCaCert() {
        CaCertEntity sub2 = caCertRepository.findBySerial("serial-sub2").get();
        CaCertEntity sub1 = sub2.getIssuer();
        CaCertEntity root = sub1.getIssuer();

        System.out.println("sub2: " + sub2.getSubjectDn());
        System.out.println("sub1 " + sub1.getSubjectDn());
        System.out.println("root " + root.getSubjectDn());
    }

    @Test
    void findPathLengthTest() {
        var now = LocalDateTime.now();

        List<CaCertEntity> caCertList = caCertRepository
                .findAllByPathLengthAndLastIssuedTrueAndValidTrueAndNotAfterLessThanEqual(PathLength.subCa1(),
                        now);

        System.out.println("CA Certs : " + caCertList.size());
    }

    @Test
    void findPathLengthTest2() {
        List<CaCertEntity> allByPathLength = caCertRepository.findAllByPathLength(PathLength.subCa1());

        System.out.println("CA Certs : " + allByPathLength.size());
    }
}
