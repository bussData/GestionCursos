package pe.edu.tecsup.lms.enrollment.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.lms.enrollment.domain.model.Enrollment;
import pe.edu.tecsup.lms.enrollment.domain.repository.EnrollmentRepository;
import pe.edu.tecsup.lms.enrollment.infrastructure.persistence.entity.EnrollmentJpaEntity;
import pe.edu.tecsup.lms.enrollment.infrastructure.persistence.mapper.EnrollmentJpaMapper;
import pe.edu.tecsup.lms.enrollment.infrastructure.persistence.repository.JpaEnrollmentRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EnrollmentRepositoryAdapter  implements EnrollmentRepository {

    private final JpaEnrollmentRepository jpaRepository;
    private final EnrollmentJpaMapper mapper;

    @Override
    public Enrollment save(Enrollment enrollment) {
        EnrollmentJpaEntity entity = mapper.toEntity(enrollment);
        EnrollmentJpaEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

}
