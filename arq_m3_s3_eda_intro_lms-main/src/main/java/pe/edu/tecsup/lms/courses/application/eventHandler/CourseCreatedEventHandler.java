package pe.edu.tecsup.lms.courses.application.eventHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.lms.shared.infrastructure.dlq.DeadLetterQueue;

import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor  // Agregar constructor para inyección de dependencias
public class CourseCreatedEventHandler {

    private final Random random = new Random();
    private final DeadLetterQueue dlq;  // Inyectar la DLQ

    @Async("eventExecutor")
    @EventListener
    // Agregar caracteristicas de reintento
    @Retryable(
            maxAttempts = 1,  // Cantidad de reintentos
            backoff = @Backoff(delay = 1000,
                    multiplier = 2))
    public void handleCourseCreated(CourseCreatedEventHandler event) throws InterruptedException{
        log.info("Processing course creation ........ : {}", event);

        if (this.random.nextBoolean()) {
            log.info("Processing course creation take longer times ........ : {}", event);
            throw new RuntimeException("Course creation failed");
        } else {
            log.info("Course creation successfully processed");
        }
    }

    @Recover
    public void recover(RuntimeException e, CourseCreatedEventHandler event){
        log.error("All retries out for recover exception : {}", e.getMessage());
    }
}
