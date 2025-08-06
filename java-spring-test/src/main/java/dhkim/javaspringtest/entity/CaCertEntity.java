package dhkim.javaspringtest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "ca_cert")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CaCertEntity extends BaseCertificateEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issuer_id")
    private CaCertEntity issuer;

    @Column(name = "mo_id", nullable = false)
    private String moId;

    @Column(name = "ak_id", nullable = false)
    private Boolean akId;

    @Column(name = "sk_id", nullable = false)
    private Boolean skId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "path_length"))
    private PathLength pathLength;

    @Column(name = "provider_id", length = 10)
    private String providerId;

    @Column(name = "wca_id", length = 25, nullable = false)
    private String wcaId;

    @Builder
    private CaCertEntity(String cert, String serial, String subjectDn, LocalDateTime notAfter, LocalDateTime notBefore,
                         Boolean valid, Boolean lastIssued, String keyAlgo, String keySpec, String signAlgo,
                         String moId, Boolean akId, Boolean skId, PathLength pathLength, String providerId,
                         String wcaId, CaCertEntity issuer) {
        super(cert, serial, subjectDn, notAfter, notBefore, valid, lastIssued, keyAlgo, keySpec, signAlgo);
        this.moId = moId;
        this.akId = akId;
        this.skId = skId;
        this.pathLength = pathLength;
        this.providerId = providerId;
        this.wcaId = wcaId;
        this.issuer = issuer;
    }

    public boolean isRoot() {
        return this.issuer == null && pathLength == null;
    }

    public String findIssuerId() {
        return this.issuer.getId();
    }

    public void changeIssuer(CaCertEntity issuer) {
        this.issuer = issuer;
    }
}
