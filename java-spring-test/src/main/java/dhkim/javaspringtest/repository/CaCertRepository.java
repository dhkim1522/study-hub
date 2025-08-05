package dhkim.javaspringtest.repository;

import dhkim.javaspringtest.entity.CaCertEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CaCertRepository extends JpaRepository<CaCertEntity, String> {
    Optional<CaCertEntity> findBySerial(String serial);
}
