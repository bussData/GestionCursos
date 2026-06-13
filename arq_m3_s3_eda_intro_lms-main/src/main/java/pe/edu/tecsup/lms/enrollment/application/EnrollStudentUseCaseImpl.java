package pe.edu.tecsup.lms.enrollment.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.edu.tecsup.lms.enrollment.domain.event.StudentEnrolledEvent;
import pe.edu.tecsup.lms.enrollment.domain.model.Enrollment;
import pe.edu.tecsup.lms.enrollment.domain.repository.EnrollmentRepository;
import pe.edu.tecsup.lms.shared.domain.event.EventPublisher;

@Slf4j
@RequiredArgsConstructor
public class EnrollStudentUseCaseImpl implements EnrollStudentUseCase {

        private final EnrollmentRepository repository;

        private final EventPublisher eventPublisher;

        @Override
        public Enrollment createEnrollment( Long studentId, Long courseId, String email) {

            Enrollment enrollment =  Enrollment.create( studentId, courseId, email);

            Enrollment saved = repository.save(enrollment);

            log.info( "Enrollment created: {}", saved.getId());

            // Crear el evento
            StudentEnrolledEvent event =
                    new StudentEnrolledEvent(
                            saved.getStudentId(),
                            saved.getCourseId(),
                            saved.getStudentEmail(),
                            saved.getStatus(),
                            saved.getEnrolledAt());

            // Publicar el evento
            this.eventPublisher.publish(event);

            return saved;
        }
}
