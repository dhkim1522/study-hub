package dhkim.javaspringtest.dto;

import dhkim.javaspringtest.entity.CaCertEntity;
import dhkim.javaspringtest.entity.CertStatus;
import dhkim.javaspringtest.entity.PathLength;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class CaCertDto {

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
        private String moId;
        private Boolean akId;
        private Boolean skId;
        private PathLength pathLength;
        private String providerId;
        private String wcaId;
        private String issuerId; // For linking to an existing issuer CA
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
        private String moId;
        private Boolean akId;
        private Boolean skId;
        private Integer pathLength;
        private String providerId;
        private String wcaId;
        private String issuerId;
        private CertStatus status;
        private LocalDateTime created;
        private LocalDateTime updated;

        public static Response from(CaCertEntity caCertEntity) {
            return Response.builder()
                    .id(caCertEntity.getId())
                    .cert(caCertEntity.getCert())
                    .serial(caCertEntity.getSerial())
                    .subjectDn(caCertEntity.getSubjectDn())
                    .notAfter(caCertEntity.getNotAfter())
                    .notBefore(caCertEntity.getNotBefore())
                    .valid(caCertEntity.getValid())
                    .lastIssued(caCertEntity.getLastIssued())
                    .keyAlgo(caCertEntity.getKeyAlgo())
                    .keySpec(caCertEntity.getKeySpec())
                    .signAlgo(caCertEntity.getSignAlgo())
                    .moId(caCertEntity.getMoId())
                    .akId(caCertEntity.getAkId())
                    .skId(caCertEntity.getSkId())
                    .pathLength(caCertEntity.getPathLength().value())
                    .providerId(caCertEntity.getProviderId())
                    .wcaId(caCertEntity.getWcaId())
                    .issuerId(caCertEntity.getIssuer() != null ? caCertEntity.getIssuer().getId() : null)
                    .status(caCertEntity.getStatus())
                    .created(caCertEntity.getCreated())
                    .updated(caCertEntity.getUpdated())
                    .build();
        }
    }
}
