package pe.edu.tecsup.lms.courses.application.eventHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class StudentEnrolledEventHandler {

    private final Random random = new Random();

    @Async("eventExecutor")
    @EventListener
    // Agregar caracteristicas de reintento
    @Retryable(
            maxAttempts = 2,  // Cantidad de reintentos
            backoff = @Backoff(delay = 1000,
                    multiplier = 2))
    public void handleCourseCreated(StudentEnrolledEventHandler event) throws  InterruptedException{
        log.info("Processing Student Enrollement ........ : {}", event);

        if (this.random.nextBoolean()) {
            log.info("Processing Enrollement take longer times ........ : {}", event);
            throw new RuntimeException("Enrollement failed");
        } else {
            log.info("Enrollement successfully processed");
        }

    }


    @Recover
    public void recover(RuntimeException e, StudentEnrolledEventHandler event){
        log.error("All retries out for recover exception : {}", e.getMessage());
    }
}
