package pe.edu.tecsup.lms.enrollment.domain.repository;

import pe.edu.tecsup.lms.courses.domain.model.Course;
import pe.edu.tecsup.lms.enrollment.domain.model.Enrollment;

public interface EnrollmentRepository {
    Enrollment save(Enrollment enrollment) ;
}
