package pe.edu.tecsup.lms.lesson.infrastructure.web.dto;


import lombok.Data;

@Data
public class CreateLessonRequest {


    private Long studentId;

    private Long lessonId;

    private Long courseId;
}
