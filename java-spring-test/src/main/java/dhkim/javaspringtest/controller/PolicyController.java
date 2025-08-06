package dhkim.javaspringtest.controller;

import dhkim.javaspringtest.dto.PolicyDto;
import dhkim.javaspringtest.service.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
@RequiredArgsConstructor
public class PolicyController {

    private final PolicyService policyService;

    @PostMapping
    public ResponseEntity<PolicyDto.Response> createPolicy(@RequestBody PolicyDto.Request request) {
        PolicyDto.Response response = policyService.createPolicy(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PolicyDto.Response> getPolicyById(@PathVariable Long id) {
        PolicyDto.Response response = policyService.getPolicyById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PolicyDto.Response>> getAllPolicies() {
        List<PolicyDto.Response> response = policyService.getAllPolicies();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PolicyDto.Response> updatePolicy(@PathVariable Long id, @RequestBody PolicyDto.Request request) {
        PolicyDto.Response response = policyService.updatePolicy(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePolicy(@PathVariable Long id) {
        policyService.deletePolicy(id);
        return ResponseEntity.noContent().build();
    }
}
