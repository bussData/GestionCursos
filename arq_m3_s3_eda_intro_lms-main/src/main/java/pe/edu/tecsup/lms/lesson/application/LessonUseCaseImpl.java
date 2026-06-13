package pe.edu.tecsup.lms.lesson.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.edu.tecsup.lms.enrollment.application.EnrollStudentUseCase;
import pe.edu.tecsup.lms.lesson.domain.event.LessonCompletedEvent;
import pe.edu.tecsup.lms.lesson.domain.model.Lesson;
import pe.edu.tecsup.lms.lesson.domain.repository.LessonRepository;
import pe.edu.tecsup.lms.shared.domain.event.EventPublisher;

@Slf4j
@RequiredArgsConstructor
public class LessonUseCaseImpl implements LessonUseCase {

    private final LessonRepository repository;

    private final EventPublisher eventPublisher;

    @Override
    public Lesson completeLesson(Long studentId, Long lessonId,Long courseId) {

        Lesson lesson =  Lesson.complete( studentId, lessonId,courseId);
        Lesson saved =  repository.save(lesson);

        log.info(
                "Lesson completed: {}",
                saved.getLessonId());

        // Crear el evento
        LessonCompletedEvent event =
                new LessonCompletedEvent(
                        saved.getStudentId(),
                        saved.getLessonId(),
                        saved.getCourseId(),
                        saved.getCompletedAt());

        // Publicar el evento
        this.eventPublisher.publish(event);

        return saved;
    }
}
