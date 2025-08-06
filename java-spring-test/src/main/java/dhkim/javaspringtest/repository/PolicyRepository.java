package dhkim.javaspringtest.repository;

import dhkim.javaspringtest.entity.CaCertEntity;
import dhkim.javaspringtest.entity.PolicyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PolicyRepository extends JpaRepository<PolicyEntity, Long> {
    List<PolicyEntity> findAllByCaCert(CaCertEntity caCert);
}
