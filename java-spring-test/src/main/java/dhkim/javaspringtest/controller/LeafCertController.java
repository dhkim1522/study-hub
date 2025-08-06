package dhkim.javaspringtest.controller;

import dhkim.javaspringtest.dto.LeafCertDto;
import dhkim.javaspringtest.service.LeafCertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaf-certs")
@RequiredArgsConstructor
public class LeafCertController {

    private final LeafCertService leafCertService;

    @PostMapping
    public ResponseEntity<LeafCertDto.Response> createLeafCert(@RequestBody LeafCertDto.Request request) {
        LeafCertDto.Response response = leafCertService.createLeafCert(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeafCertDto.Response> getLeafCertById(@PathVariable Long id) {
        LeafCertDto.Response response = leafCertService.getLeafCertById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<LeafCertDto.Response>> getAllLeafCerts() {
        List<LeafCertDto.Response> response = leafCertService.getAllLeafCerts();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeafCertDto.Response> updateLeafCert(@PathVariable Long id, @RequestBody LeafCertDto.Request request) {
        LeafCertDto.Response response = leafCertService.updateLeafCert(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeafCert(@PathVariable Long id) {
        leafCertService.deleteLeafCert(id);
        return ResponseEntity.noContent().build();
    }
}
