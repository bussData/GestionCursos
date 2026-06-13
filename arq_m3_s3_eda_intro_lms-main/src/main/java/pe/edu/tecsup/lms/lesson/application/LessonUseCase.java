package pe.edu.tecsup.lms.lesson.application;

import pe.edu.tecsup.lms.lesson.domain.model.Lesson;

public interface LessonUseCase {

    Lesson completeLesson(Long studentId, Long lessonId, Long courseId);
}
