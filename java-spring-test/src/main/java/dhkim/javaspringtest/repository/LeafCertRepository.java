package dhkim.javaspringtest.repository;

import dhkim.javaspringtest.entity.LeafCertEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeafCertRepository extends JpaRepository<LeafCertEntity, Long> {
}
