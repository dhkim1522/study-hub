package dhkim.javaspringtest.repository;

import dhkim.javaspringtest.entity.CaCertEntity;
import dhkim.javaspringtest.entity.LeafCertEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeafCertRepository extends JpaRepository<LeafCertEntity, Long> {
    List<LeafCertEntity> findAllByIssuer(CaCertEntity issuer);
}
