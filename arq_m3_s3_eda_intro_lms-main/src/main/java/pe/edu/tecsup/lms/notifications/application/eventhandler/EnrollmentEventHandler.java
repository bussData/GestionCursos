package pe.edu.tecsup.lms.notifications.application.eventhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.lms.enrollment.domain.event.StudentEnrolledEvent;

/**
 * Es el consumidor de eventos
 */
@Slf4j
@Component
public class EnrollmentEventHandler {
    @EventListener
    public void handleSendWelcomeEmail( StudentEnrolledEvent event) {

        log.info( "Sending welcome email to student: {}", event.getStudentEmail());
    }

    @EventListener
    public void handleUpdateCourseStatistics( StudentEnrolledEvent event) {

        log.info("Updating statistics for course: {}", event.getCourseId());
    }

}
