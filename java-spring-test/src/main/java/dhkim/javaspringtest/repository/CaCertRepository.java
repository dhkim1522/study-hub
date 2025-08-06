package dhkim.javaspringtest.repository;

import dhkim.javaspringtest.entity.CaCertEntity;
import dhkim.javaspringtest.entity.PathLength;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CaCertRepository extends JpaRepository<CaCertEntity, String> {
    Optional<CaCertEntity> findBySerial(String serial);
    List<CaCertEntity> findAllByPathLength(PathLength pathLength);

    List<CaCertEntity> findAllByPathLengthAndLastIssuedTrueAndValidTrueAndNotAfterGreaterThanEqual(PathLength pathLength, LocalDateTime notAfter);
    List<CaCertEntity> findAllByPathLengthAndLastIssuedTrueAndValidTrueAndNotAfterLessThanEqual(PathLength pathLength, LocalDateTime notAfter);
}
