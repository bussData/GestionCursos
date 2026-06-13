package pe.edu.tecsup.lms.lesson.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import pe.edu.tecsup.lms.shared.domain.event.DomainEvent;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class LessonCompletedEvent extends DomainEvent {
    private final Long studentId;

    private final Long lessonId;

    private final Long courseId;


    private final LocalDateTime completedAt;
}
