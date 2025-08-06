package dhkim.javaspringtest.service;

import dhkim.javaspringtest.dto.PolicyDto;
import dhkim.javaspringtest.entity.CaCertEntity;
import dhkim.javaspringtest.entity.PolicyEntity;
import dhkim.javaspringtest.repository.CaCertRepository;
import dhkim.javaspringtest.repository.PolicyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PolicyService {

    private final PolicyRepository policyRepository;
    private final CaCertRepository caCertRepository;

    @Transactional
    public PolicyDto.Response createPolicy(PolicyDto.Request request) {
        CaCertEntity caCert = null;
        if (request.getCaCertId() != null) {
            caCert = caCertRepository.findById(request.getCaCertId())
                    .orElseThrow(() -> new EntityNotFoundException("CA Certificate not found with ID: " + request.getCaCertId()));
        }

        PolicyEntity policyEntity = PolicyEntity.builder()
                .caCert(caCert)
                .name(request.getName())
                .providerId(request.getProviderId())
                .build();

        PolicyEntity savedPolicy = policyRepository.save(policyEntity);
        return PolicyDto.Response.from(savedPolicy);
    }

    public PolicyDto.Response getPolicyById(Long id) {
        PolicyEntity policyEntity = policyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Policy not found with ID: " + id));
        return PolicyDto.Response.from(policyEntity);
    }

    public List<PolicyDto.Response> getAllPolicies() {
        return policyRepository.findAll().stream()
                .map(PolicyDto.Response::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public PolicyDto.Response updatePolicy(Long id, PolicyDto.Request request) {
        PolicyEntity existingPolicy = policyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Policy not found with ID: " + id));

        CaCertEntity newCaCert = null;
        if (request.getCaCertId() != null) {
            newCaCert = caCertRepository.findById(request.getCaCertId())
                    .orElseThrow(() -> new EntityNotFoundException("CA Certificate not found with ID: " + request.getCaCertId()));
        }

        existingPolicy.updatePolicy(request.getName(), newCaCert);

        PolicyEntity updatedPolicy = policyRepository.save(existingPolicy);
        return PolicyDto.Response.from(updatedPolicy);
    }

    @Transactional
    public void deletePolicy(Long id) {
        if (!policyRepository.existsById(id)) {
            throw new EntityNotFoundException("Policy not found with ID: " + id);
        }
        policyRepository.deleteById(id);
    }
}
