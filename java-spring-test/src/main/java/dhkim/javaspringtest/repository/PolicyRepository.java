package dhkim.javaspringtest.repository;

import dhkim.javaspringtest.entity.PolicyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository extends JpaRepository<PolicyEntity, Long> {
}
