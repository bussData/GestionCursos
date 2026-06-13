package pe.edu.tecsup.lms.lesson.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {

    private Long id;

    private Long studentId;

    private Long lessonId;

    private Long courseId;

    private LessonStatus status;

    private LocalDateTime completedAt;


    public static Lesson complete(
            Long studentId,
            Long lessonId,
            Long courseId) {

        if (studentId == null) {
            throw new IllegalArgumentException(
                    "studentId is required");
        }

        if (lessonId == null) {
            throw new IllegalArgumentException(
                    "lessonId is required");
        }

        if (courseId == null) {
            throw new IllegalArgumentException(
                    "courseId is required");
        }

        Lesson lesson = new Lesson();

        lesson.studentId = studentId;
        lesson.lessonId = lessonId;
        lesson.courseId = courseId;
        lesson.status = LessonStatus.COMPLETED;
        lesson.completedAt = LocalDateTime.now();

        return lesson;
    }

    public enum LessonStatus {
        COMPLETED,

    }

}
