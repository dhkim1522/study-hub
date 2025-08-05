package dhkim.javaspringtest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CertMetadata {
    @Column(name = "serial", length = 32, nullable = false)
    private String serial;

    @Column(name = "subject_dn", nullable = false)
    private String subjectDn;

    @Column(name = "not_after", nullable = false)
    private LocalDateTime notAfter;

    @Column(name = "not_before", nullable = false)
    private LocalDateTime notBefore;
}
