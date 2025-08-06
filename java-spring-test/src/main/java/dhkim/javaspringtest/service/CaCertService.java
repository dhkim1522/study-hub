package dhkim.javaspringtest.service;

import dhkim.javaspringtest.dto.CaCertDto;
import dhkim.javaspringtest.entity.CaCertEntity;
import dhkim.javaspringtest.entity.LeafCertEntity;
import dhkim.javaspringtest.entity.PathLength;
import dhkim.javaspringtest.entity.PolicyEntity;
import dhkim.javaspringtest.repository.CaCertRepository;
import dhkim.javaspringtest.repository.LeafCertRepository;
import dhkim.javaspringtest.repository.PolicyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CaCertService {

    private final CaCertRepository caCertRepository;
    private final LeafCertRepository leafCertRepository;
    private final PolicyRepository policyRepository;

    @Transactional
    public CaCertDto.Response createCaCert(CaCertDto.Request request) {
        CaCertEntity issuer = Optional.ofNullable(request.getIssuerId())
                .flatMap(caCertRepository::findById)
                .orElse(null);

        CaCertEntity caCertEntity = CaCertEntity.builder()
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
                .moId(request.getMoId())
                .akId(request.getAkId())
                .skId(request.getSkId())
                .pathLength(request.getPathLength())
                .providerId(request.getProviderId())
                .wcaId(request.getWcaId())
                .issuer(issuer)
                .build();

        CaCertEntity savedCaCert = caCertRepository.save(caCertEntity);
        return CaCertDto.Response.from(savedCaCert);
    }

    public CaCertDto.Response getCaCertById(String id) {
        CaCertEntity caCertEntity = caCertRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CA Certificate not found with ID: " + id));
        return CaCertDto.Response.from(caCertEntity);
    }

    public List<CaCertDto.Response> getAllCaCerts() {
        return caCertRepository.findAll().stream()
                .map(CaCertDto.Response::from)
                .collect(Collectors.toList());
    }

    public List<CaCertDto.Response> getCaCertsByPathLength(PathLength pathLength) {
        return caCertRepository.findAllByPathLength(pathLength).stream()
                .map(CaCertDto.Response::from)
                .collect(Collectors.toList());
    }

    public List<CaCertDto.Response> getValidAndLastIssuedCaCertsByPathLengthAndNotAfterGreaterThanEqual(PathLength pathLength, LocalDateTime notAfter) {
        return caCertRepository.findAllByPathLengthAndLastIssuedTrueAndValidTrueAndNotAfterGreaterThanEqual(pathLength, notAfter).stream()
                .map(CaCertDto.Response::from)
                .collect(Collectors.toList());
    }

    public List<CaCertDto.Response> getValidAndLastIssuedCaCertsByPathLengthAndNotAfterLessThanEqual(PathLength pathLength, LocalDateTime notAfter) {
        return caCertRepository.findAllByPathLengthAndLastIssuedTrueAndValidTrueAndNotAfterLessThanEqual(pathLength, notAfter).stream()
                .map(CaCertDto.Response::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public CaCertDto.Response updateCaCert(String id, CaCertDto.Request request) {
        CaCertEntity existingCaCert = caCertRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CA Certificate not found with ID: " + id));

        CaCertEntity issuer = Optional.ofNullable(request.getIssuerId())
                .flatMap(caCertRepository::findById)
                .orElse(null);

        // Update fields. Note: Some fields might not be intended for update via API, adjust as needed.
        // For simplicity, I'm assuming all fields in Request can be updated.
        // In a real application, you'd selectively update fields or use a dedicated update DTO.
        existingCaCert.changeIssuer(issuer); // Example of updating a relationship

        // Using reflection or a builder for partial updates can be more robust.
        // For now, I'll assume direct field setting is acceptable for demonstration.
        // However, the entity's builder is private, so direct setting is not possible.
        // A better approach would be to add update methods to the entity or use MapStruct.
        // For this example, I'll create a new entity and copy properties, then delete the old one.
        // This is NOT ideal for JPA updates, but demonstrates the concept of changing data.
        // A proper update would involve setters on the entity or a dedicated update method.
        // Given the current entity design, I'll only update the issuer for now as it has a setter.
        // For other fields, you would need to add setters to BaseCertificateEntity or CaCertEntity.

        // As the entity fields are final (no setters), we can't directly update them.
        // The only mutable field with a setter is 'issuer'.
        // To update other fields, you would need to add setters to BaseCertificateEntity and CaCertEntity
        // or create a new entity instance and replace the old one (less efficient for JPA).
        // For demonstration, I'll only update the issuer if provided.

        // If you need to update other fields, you must add setters to your entity classes.
        // For example:
        // existingCaCert.setCert(request.getCert());
        // existingCaCert.setSerial(request.getSerial());
        // ... and so on.

        // Assuming you add setters to your entities for update, the code would look like this:
        // existingCaCert.setCert(request.getCert());
        // existingCaCert.setSerial(request.getSerial());
        // existingCaCert.setSubjectDn(request.getSubjectDn());
        // existingCaCert.setNotAfter(request.getNotAfter());
        // existingCaCert.setNotBefore(request.getNotBefore());
        // existingCaCert.setValid(request.getValid());
        // existingCaCert.setLastIssued(request.getLastIssued());
        // existingCaCert.setKeyAlgo(request.getKeyAlgo());
        // existingCaCert.setKeySpec(request.getKeySpec());
        // existingCaCert.setSignAlgo(request.getSignAlgo());
        // existingCaCert.setMoId(request.getMoId());
        // existingCaCert.setAkId(request.getAkId());
        // existingCaCert.setSkId(request.getSkId());
        // existingCaCert.setPathLength(request.getPathLength());
        // existingCaCert.setProviderId(request.getProviderId());
        // existingCaCert.setWcaId(request.getWcaId());

        // For now, I'll just save the existing entity after potentially changing the issuer.
        // If other fields need to be updated, you MUST add setters to the entity classes.
        CaCertEntity updatedCaCert = caCertRepository.save(existingCaCert);
        return CaCertDto.Response.from(updatedCaCert);
    }

    @Transactional
    public CaCertDto.Response updateCaCertIssuer(String id, String newIssuerId) {
        CaCertEntity existingCaCert = caCertRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CA Certificate not found with ID: " + id));

        CaCertEntity newIssuer = Optional.ofNullable(newIssuerId)
                .flatMap(caCertRepository::findById)
                .orElse(null);

        // Validate the change based on the existing certificate's pathLength and the new issuer's pathLength
        // This is where the domain logic for valid issuer changes should be implemented.
        // For simplicity, I'm only updating the issuer here.
        // A more robust solution would involve re-validating the entire certificate state
        // after the issuer change to ensure it adheres to all rules (e.g., pathLength, providerId).

        existingCaCert.changeIssuer(newIssuer);
        // No need to save, dirty checking handles it.
        return CaCertDto.Response.from(existingCaCert);
    }

    @Transactional
    public void deleteCaCert(String id) {
        CaCertEntity caCertToDelete = caCertRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CA Certificate not found with ID: " + id));

        // Delete associated LeafCertEntity instances
        List<LeafCertEntity> leafCerts = leafCertRepository.findAllByIssuer(caCertToDelete);
        leafCertRepository.deleteAll(leafCerts);

        // Delete associated PolicyEntity instances
        List<PolicyEntity> policies = policyRepository.findAllByCaCert(caCertToDelete);
        policyRepository.deleteAll(policies);

        caCertRepository.delete(caCertToDelete);
    }

    public CaCertDto.Response getCaCertChain(String id) {
        CaCertEntity caCert = caCertRepository.findByIdWithIssuerGraph(id)
                .orElseThrow(() -> new EntityNotFoundException("CA Certificate not found with ID: " + id));

        // The recursive population of issuerCert happens within CaCertDto.Response.from()
        // With findCaCertByIdWithIssuerGraph, the first level of issuer is eagerly fetched.
        // Subsequent levels will still trigger lazy loading if not already in the session.
        // For fetching deeper chains in a single query, consider a custom JPQL query with JOIN FETCH
        // or a native query with recursive CTEs if supported by your database.
        return CaCertDto.Response.from(caCert);
    }
}
