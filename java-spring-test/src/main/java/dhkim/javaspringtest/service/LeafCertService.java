package dhkim.javaspringtest.service;

import dhkim.javaspringtest.dto.LeafCertDto;
import dhkim.javaspringtest.entity.CaCertEntity;
import dhkim.javaspringtest.entity.LeafCertEntity;
import dhkim.javaspringtest.repository.CaCertRepository;
import dhkim.javaspringtest.repository.LeafCertRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LeafCertService {

    private final LeafCertRepository leafCertRepository;
    private final CaCertRepository caCertRepository;

    @Transactional
    public LeafCertDto.Response createLeafCert(LeafCertDto.Request request) {
        CaCertEntity issuer = caCertRepository.findById(request.getIssuerId())
                    .orElseThrow(() -> new EntityNotFoundException("Issuer CA not found with ID: " + request.getIssuerId()));

        LeafCertEntity leafCertEntity = LeafCertEntity.builder()
                .cert(request.getCert())
                .serial(request.getSerial())
                .subjectDn(request.getSubjectDn())
                .notAfter(request.getNotAfter())
                .notBefore(request.getNotBefore())
                .valid(request.getValid())
                .lastIssued(request.getLastIssued())
                .keyAlgo(request.getKeyAlgo())
                .keySpec(request.getKeySpec())
                .signAlgo(request.getSignAlgo())
                .issuer(issuer)
                .cn(request.getCn())
                .pfxCert(request.getPfxCert())
                .build();

        LeafCertEntity savedLeafCert = leafCertRepository.save(leafCertEntity);
        return LeafCertDto.Response.from(savedLeafCert);
    }

    public LeafCertDto.Response getLeafCertById(Long id) {
        LeafCertEntity leafCertEntity = leafCertRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Leaf Certificate not found with ID: " + id));
        return LeafCertDto.Response.from(leafCertEntity);
    }

    public List<LeafCertDto.Response> getAllLeafCerts() {
        return leafCertRepository.findAll().stream()
                .map(LeafCertDto.Response::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public LeafCertDto.Response updateLeafCert(Long id, LeafCertDto.Request request) {
        LeafCertEntity existingLeafCert = leafCertRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Leaf Certificate not found with ID: " + id));

        CaCertEntity issuer = caCertRepository.findById(request.getIssuerId())
                .orElseThrow(() -> new EntityNotFoundException("Issuer CA not found with ID: " + request.getIssuerId()));

        // Similar to CaCertEntity, LeafCertEntity fields are mostly immutable after creation.
        // To update other fields, you would need to add setters to BaseCertificateEntity and LeafCertEntity
        // or create a new entity instance and replace the old one (less efficient for JPA).
        // For demonstration, I'll only update the issuer if provided, assuming a setter for issuer exists.
        // If you need to update other fields, you MUST add setters to your entity classes.

        // For example, if you add setters:
        // existingLeafCert.setCert(request.getCert());
        // existingLeafCert.setSerial(request.getSerial());
        // ... and so on.

        LeafCertEntity updatedLeafCert = leafCertRepository.save(existingLeafCert);
        return LeafCertDto.Response.from(updatedLeafCert);
    }

    @Transactional
    public void deleteLeafCert(Long id) {
        if (!leafCertRepository.existsById(id)) {
            throw new EntityNotFoundException("Leaf Certificate not found with ID: " + id);
        }
        leafCertRepository.deleteById(id);
    }
}
