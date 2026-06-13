package pe.edu.tecsup.lms.analytics.application.eventhandler;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.lms.courses.domain.event.CourseCreatedEvent;
import pe.edu.tecsup.lms.courses.domain.event.CoursePublishedEvent;
import pe.edu.tecsup.lms.enrollment.domain.event.StudentEnrolledEvent;

/**StudentEnrolledEvent
 * Es el consumidor de eventos
 */
@Slf4j
@Component
public class AnalyticsEnrollmentEventHandler {

    @Async("eventExecutor")
    @EventListener
    public void handleCreateMaterialAccess(StudentEnrolledEvent event) throws InterruptedException {

        log.info("Starting analytics ........ : {}", event);
        log.info("Creating material access for student: {}", event.getStudentId());
        Thread.sleep(4000);
        log.info("Ending analytics ........ : {}", event.getStudentId());

    }


}
