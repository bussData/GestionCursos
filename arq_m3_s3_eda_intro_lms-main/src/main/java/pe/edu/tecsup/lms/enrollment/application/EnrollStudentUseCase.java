package pe.edu.tecsup.lms.enrollment.application;

import pe.edu.tecsup.lms.courses.domain.model.Course;
import pe.edu.tecsup.lms.enrollment.domain.model.Enrollment;

public interface EnrollStudentUseCase {

    Enrollment createEnrollment(Long studentId, Long courseId, String email);
}
