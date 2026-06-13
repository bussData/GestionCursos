package pe.edu.tecsup.lms.enrollment.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import pe.edu.tecsup.lms.courses.domain.model.Course;
import pe.edu.tecsup.lms.courses.infrastructure.persistence.entity.CourseJpaEntity;
import pe.edu.tecsup.lms.enrollment.domain.model.Enrollment;
import pe.edu.tecsup.lms.enrollment.infrastructure.persistence.entity.EnrollmentJpaEntity;

@Mapper(componentModel = "spring")
public interface EnrollmentJpaMapper {

    EnrollmentJpaEntity toEntity(Enrollment enrollment);

    Enrollment toDomain(EnrollmentJpaEntity entity);
}
