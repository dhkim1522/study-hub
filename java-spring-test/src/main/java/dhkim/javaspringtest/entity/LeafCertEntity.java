package dhkim.javaspringtest.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "leaf_cert", indexes = {
        @Index(name = "leaf_cn_idx", columnList = "cn")
})
public class LeafCertEntity extends BaseCertificateEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issuer_id")
    private CaCertEntity issuer;

    @Column(name = "cn", nullable = false)
    private String cn;

    @Column(name = "pfx_cert", length = 6000)
    private String pfxCert;

    @Builder
    private LeafCertEntity(String cert, String serial, String subjectDn, LocalDateTime notAfter, LocalDateTime notBefore,
                           Boolean valid, Boolean lastIssued, String keyAlgo, String keySpec, String signAlgo,
                           CaCertEntity issuer, String cn, String pfxCert) {
        super(cert, serial, subjectDn, notAfter, notBefore, valid, lastIssued, keyAlgo, keySpec, signAlgo);
        this.issuer = issuer;
        this.cn = cn;
        this.pfxCert = pfxCert;
    }

    public String findIssuerId() {
        return this.issuer.getId();
    }
}
