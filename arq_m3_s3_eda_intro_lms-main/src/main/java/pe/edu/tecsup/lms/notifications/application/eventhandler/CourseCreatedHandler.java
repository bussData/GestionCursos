package pe.edu.tecsup.lms.notifications.application.eventhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.lms.courses.domain.event.CourseCreatedEvent;
import pe.edu.tecsup.lms.courses.domain.event.CoursePublishedEvent;

import java.util.Random;

@Slf4j
@Component
public class CourseCreatedHandler {


    private final Random random = new Random();

    @Async("eventExecutor")
    @EventListener
    // Agregar caracteristicas de reintento
    @Retryable(
            maxAttempts = 2,  // Cantidad de reintentos
            backoff = @Backoff(delay = 1000,
                    multiplier = 2))
    public void handleCourseCreated(CourseCreatedEvent event) throws InterruptedException {
        log.info("Processing created handler ........ : {}", event);

        if(this.random.nextBoolean()){
            log.info("Processing created take longer times ........ : {}", event);
            throw new RuntimeException("Course failed");
        } else {
            log.info("Course successfully processed");
        }
    }

    @Recover
    public void recover(RuntimeException e , CourseCreatedEvent event){
        log.error("All retries for recover exception course: {}", e.getMessage());

    }


    }
