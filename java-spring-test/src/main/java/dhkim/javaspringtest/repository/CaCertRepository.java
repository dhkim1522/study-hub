package dhkim.javaspringtest.repository;

import dhkim.javaspringtest.entity.CaCertEntity;
import dhkim.javaspringtest.entity.PathLength;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    // 일반적인 findById (EntityGraph 사용 X)
    Optional<CaCertEntity> findById(String id);

    // Fetch Join을 사용한 EntityGraph 적용
    @EntityGraph(attributePaths = {"issuer"})
    @Query("SELECT c FROM CaCertEntity c WHERE c.id = :id")
    Optional<CaCertEntity> findByIdWithIssuerGraph(@Param("id") String id);
}
