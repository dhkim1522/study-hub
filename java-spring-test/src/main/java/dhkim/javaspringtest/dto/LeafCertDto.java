package dhkim.javaspringtest.dto;

import dhkim.javaspringtest.entity.CertStatus;
import dhkim.javaspringtest.entity.LeafCertEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class LeafCertDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String cert;
        private String serial;
        private String subjectDn;
        private LocalDateTime notAfter;
        private LocalDateTime notBefore;
        private Boolean valid;
        private Boolean lastIssued;
        private String keyAlgo;
        private String keySpec;
        private String signAlgo;
        private String issuerId;
        private String cn;
        private String pfxCert;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String id;
        private String cert;
        private String serial;
        private String subjectDn;
        private LocalDateTime notAfter;
        private LocalDateTime notBefore;
        private Boolean valid;
        private Boolean lastIssued;
        private String keyAlgo;
        private String keySpec;
        private String signAlgo;
        private String issuerId;
        private String cn;
        private String pfxCert;
        private CertStatus status;
        private LocalDateTime created;
        private LocalDateTime updated;

        public static Response from(LeafCertEntity leafCertEntity) {
            return Response.builder()
                    .id(leafCertEntity.getId())
                    .cert(leafCertEntity.getCert())
                    .serial(leafCertEntity.getSerial())
                    .subjectDn(leafCertEntity.getSubjectDn())
                    .notAfter(leafCertEntity.getNotAfter())
                    .notBefore(leafCertEntity.getNotBefore())
                    .valid(leafCertEntity.getValid())
                    .lastIssued(leafCertEntity.getLastIssued())
                    .keyAlgo(leafCertEntity.getKeyAlgo())
                    .keySpec(leafCertEntity.getKeySpec())
                    .signAlgo(leafCertEntity.getSignAlgo())
                    .issuerId(leafCertEntity.getIssuer() != null ? leafCertEntity.getIssuer().getId() : null)
                    .cn(leafCertEntity.getCn())
                    .pfxCert(leafCertEntity.getPfxCert())
                    .status(leafCertEntity.getStatus())
                    .created(leafCertEntity.getCreated())
                    .updated(leafCertEntity.getUpdated())
                    .build();
        }
    }
}
