package dhkim.javaspringtest.controller;

import dhkim.javaspringtest.dto.CaCertDto;
import dhkim.javaspringtest.entity.PathLength;
import dhkim.javaspringtest.service.CaCertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/ca-certs")
@RequiredArgsConstructor
public class CaCertController {

    private final CaCertService caCertService;

    @PostMapping
    public ResponseEntity<CaCertDto.Response> createCaCert(@RequestBody CaCertDto.Request request) {
        CaCertDto.Response response = caCertService.createCaCert(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaCertDto.Response> getCaCertById(@PathVariable String id) {
        CaCertDto.Response response = caCertService.getCaCertById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CaCertDto.Response>> getAllCaCerts() {
        List<CaCertDto.Response> response = caCertService.getAllCaCerts();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-path-length")
    public ResponseEntity<List<CaCertDto.Response>> getCaCertsByPathLength(@RequestParam Integer value) {
        PathLength pathLength = new PathLength(value);
        List<CaCertDto.Response> response = caCertService.getCaCertsByPathLength(pathLength);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/valid-last-issued-after")
    public ResponseEntity<List<CaCertDto.Response>> getValidAndLastIssuedCaCertsByPathLengthAndNotAfterGreaterThanEqual(
            @RequestParam Integer value,
            @RequestParam String notAfter) {
        PathLength pathLength = new PathLength(value);
        LocalDateTime notAfterDateTime = LocalDateTime.parse(notAfter);
        List<CaCertDto.Response> response = caCertService.getValidAndLastIssuedCaCertsByPathLengthAndNotAfterGreaterThanEqual(pathLength, notAfterDateTime);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/valid-last-issued-before")
    public ResponseEntity<List<CaCertDto.Response>> getValidAndLastIssuedCaCertsByPathLengthAndNotAfterLessThanEqual(
            @RequestParam Integer value,
            @RequestParam String notAfter) {
        PathLength pathLength = new PathLength(value);
        LocalDateTime notAfterDateTime = LocalDateTime.parse(notAfter);
        List<CaCertDto.Response> response = caCertService.getValidAndLastIssuedCaCertsByPathLengthAndNotAfterLessThanEqual(pathLength, notAfterDateTime);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CaCertDto.Response> updateCaCert(@PathVariable String id, @RequestBody CaCertDto.Request request) {
        CaCertDto.Response response = caCertService.updateCaCert(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCaCert(@PathVariable String id) {
        caCertService.deleteCaCert(id);
        return ResponseEntity.noContent().build();
    }
}
