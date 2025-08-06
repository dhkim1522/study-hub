package dhkim.javaspringtest.dto;

import dhkim.javaspringtest.entity.PolicyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class PolicyDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String caCertId;
        private String name;
        private String providerId;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String id;
        private String caCertId;
        private String name;
        private String providerId;
        private LocalDateTime created;
        private LocalDateTime updated;

        public static Response from(PolicyEntity policyEntity) {
            return Response.builder()
                    .id(policyEntity.getId())
                    .caCertId(policyEntity.getCaCert() != null ? policyEntity.getCaCert().getId() : null)
                    .name(policyEntity.getName())
                    .providerId(policyEntity.getProviderId())
                    .created(policyEntity.getCreated())
                    .updated(policyEntity.getUpdated())
                    .build();
        }
    }
}
