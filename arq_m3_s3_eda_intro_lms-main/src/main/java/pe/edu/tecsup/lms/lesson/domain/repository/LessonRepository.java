package pe.edu.tecsup.lms.lesson.domain.repository;

import pe.edu.tecsup.lms.lesson.domain.model.Lesson;

public interface LessonRepository {

    Lesson save(Lesson lesson);
}
