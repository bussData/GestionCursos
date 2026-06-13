package pe.edu.tecsup.lms.enrollment.infrastructure.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.tecsup.lms.courses.application.PublishCourseUseCase;
import pe.edu.tecsup.lms.courses.application.PublishCourseUseCaseImpl;
import pe.edu.tecsup.lms.courses.domain.repository.CourseRepository;
import pe.edu.tecsup.lms.enrollment.application.EnrollStudentUseCase;
import pe.edu.tecsup.lms.enrollment.application.EnrollStudentUseCaseImpl;
import pe.edu.tecsup.lms.enrollment.application.command.EnrollmentCommandHandler;
import pe.edu.tecsup.lms.enrollment.domain.repository.EnrollmentRepository;
import pe.edu.tecsup.lms.shared.domain.event.EventPublisher;
import pe.edu.tecsup.lms.shared.infrastructure.eventsourcing.MemoryEventStore;

/**
 * CONFIGURACIÓN DE BEANS
 *
 * Registra los Use Cases (impls) detrás de su interfaz (input port).
 * El controller depende de la interfaz, no de la implementación.
 */
@Configuration
public class EnrollmentBeanConfiguration {

    @Bean
    public EnrollStudentUseCase createEnrollmentUseCase(EnrollmentRepository repository, EventPublisher eventPublisher) {

        return new EnrollStudentUseCaseImpl(repository, eventPublisher);

    }



    //para Event Sourcing:
    @Bean
    public EnrollmentCommandHandler enrollmentCommandHandler(MemoryEventStore eventStore) {
        return new EnrollmentCommandHandler(eventStore);
    }

}
