package pe.edu.tecsup.lms.notifications.application.eventhandler;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.lms.lesson.domain.event.LessonCompletedEvent;

@Slf4j
@Component
public class LessonEventHandler {
    @EventListener
    public void handleSendAchievementNotification(LessonCompletedEvent event) {

        log.info("Sending achievement notification for lesson: {}",   event.getLessonId());
    }

    @EventListener
    public void handleUpdateProgress(LessonCompletedEvent event) {

        log.info("Updating progress for student: {}",   event.getStudentId());
    }

}
