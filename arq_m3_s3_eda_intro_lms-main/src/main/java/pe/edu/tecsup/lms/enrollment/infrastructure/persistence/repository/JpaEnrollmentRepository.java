package pe.edu.tecsup.lms.enrollment.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.tecsup.lms.enrollment.infrastructure.persistence.entity.EnrollmentJpaEntity;

public interface JpaEnrollmentRepository extends JpaRepository<EnrollmentJpaEntity, Long> {
}
