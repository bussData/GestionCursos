package pe.edu.tecsup.lms.lesson.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.tecsup.lms.enrollment.application.EnrollStudentUseCase;
import pe.edu.tecsup.lms.enrollment.domain.model.Enrollment;
import pe.edu.tecsup.lms.lesson.application.LessonUseCase;
import pe.edu.tecsup.lms.lesson.domain.model.Lesson;
import pe.edu.tecsup.lms.lesson.infrastructure.web.dto.CreateLessonRequest;

@RestController
@RequestMapping("/api/lesson")
@RequiredArgsConstructor
public class LessonController {

    private final LessonUseCase lessonUseCase;

    @PostMapping
    public ResponseEntity<Lesson> completeLesson( @RequestBody CreateLessonRequest request) {

        Lesson lesson =
                lessonUseCase.completeLesson(
                        request.getStudentId(),
                        request.getLessonId(),
                        request.getCourseId()
                );

        return ResponseEntity.ok(lesson);
    }
}