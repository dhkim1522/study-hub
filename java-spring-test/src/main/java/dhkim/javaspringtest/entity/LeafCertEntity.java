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
public class LeafCertEntity extends BaseCertEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ca_cert_id")
    private CaCertEntity caCert;

    @Column(name = "cn", nullable = false)
    private String cn;

    @Column(name = "pfx_cert", length = 6000)
    private String pfxCert;

    @Builder
    public LeafCertEntity(String cert, String serial, String subjectDn, LocalDateTime notAfter, LocalDateTime notBefore,
                          Boolean valid, Boolean lastIssued, String keyAlgo, String keySpec, String signAlgo,
                          CaCertEntity caCert, String cn, String pfxCert) {
        super(cert, serial, subjectDn, notAfter, notBefore, valid, lastIssued, keyAlgo, keySpec, signAlgo);
        this.caCert = caCert;
        this.cn = cn;
        this.pfxCert = pfxCert;
    }
}
