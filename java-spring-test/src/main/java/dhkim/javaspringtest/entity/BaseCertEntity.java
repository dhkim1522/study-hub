package dhkim.javaspringtest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseCertEntity extends BaseEntity {
    @Column(name = "cert", length = 4000, nullable = false)
    private String cert;

    @Column(name = "serial", length = 32, nullable = false)
    private String serial;

    @Column(name = "subject_dn", nullable = false)
    private String subjectDn;

    @Column(name = "not_before", nullable = false)
    private LocalDateTime notBefore;

    @Column(name = "not_after", nullable = false)
    private LocalDateTime notAfter;

    @Column(name = "valid", nullable = false)
    private Boolean valid;

    @Column(name = "last_issued", nullable = false)
    private Boolean lastIssued;

    @Column(name = "key_algo", nullable = false)
    private String keyAlgo;

    @Column(name = "key_spec", nullable = false)
    private String keySpec;

    @Column(name = "sign_algo", nullable = false)
    private String signAlgo;

    public void revoke() {
        this.valid = false;
    }

    public void lastIssuedFalse() {
        this.lastIssued = false;
    }

    public boolean isOldCert() {
        return !this.lastIssued;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.notAfter);
    }

    public CertStatus getStatus() {
        if (!this.valid) return CertStatus.REVOKED;
        if (this.isExpired()) return CertStatus.EXPIRED;
        return CertStatus.VALID;
    }
}
