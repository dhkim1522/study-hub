package dhkim.javaspringtest.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "policy")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PolicyEntity extends BaseDomainEntity {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ca_cert_id")
    private CaCertEntity caCert;

    @Column(name = "name", length = 128, nullable = false)
    private String name;

    @Column(name = "provider_id", length = 10, nullable = false)
    private String providerId;

    @Builder
    public PolicyEntity(CaCertEntity caCert, String name, String providerId) {
        this.caCert = caCert;
        this.name = name;
        this.providerId = providerId;
    }

    public void updatePolicy(String newPolicyName, CaCertEntity newPolicyCert) {
        this.name = newPolicyName;
        this.caCert = newPolicyCert;
    }

    public String findCaCertId() {
        return this.caCert.getId();
    }
}
