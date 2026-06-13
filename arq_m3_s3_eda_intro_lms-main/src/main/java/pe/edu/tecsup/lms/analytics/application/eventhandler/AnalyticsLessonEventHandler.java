package pe.edu.tecsup.lms.analytics.application.eventhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.lms.lesson.domain.event.LessonCompletedEvent;

/**StudentEnrolledEvent
 * Es el consumidor de eventos
 */
@Slf4j
@Component
public class AnalyticsLessonEventHandler {

    @Async("eventExecutor")
    @EventListener
    public void handleVerifyCourseCompletion( LessonCompletedEvent event) throws InterruptedException {


        log.info("Starting analytics ........ : {}", event);
        log.info("Verifying course completion for course: {}", event.getCourseId());
        Thread.sleep(4000);
        log.info("Ending analytics ........ : {}", event.getStudentId());
    }
}
