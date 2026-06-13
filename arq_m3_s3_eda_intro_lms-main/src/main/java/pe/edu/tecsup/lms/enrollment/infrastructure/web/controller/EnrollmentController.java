package pe.edu.tecsup.lms.enrollment.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import pe.edu.tecsup.lms.enrollment.application.EnrollStudentUseCase;
import pe.edu.tecsup.lms.enrollment.application.command.EnrollStudentCommand;
import pe.edu.tecsup.lms.enrollment.application.command.EnrollmentCommandHandler;
import pe.edu.tecsup.lms.enrollment.domain.model.Enrollment;
import pe.edu.tecsup.lms.enrollment.infrastructure.web.dto.CreateEnrollmentRequest;
import pe.edu.tecsup.lms.enrollment.infrastructure.web.dto.EnrollmentRequest;
import pe.edu.tecsup.lms.enrollment.infrastructure.web.dto.EnrollmentResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollment")
@RequiredArgsConstructor
public class EnrollmentController {

    //private final EnrollStudentUseCase enrollStudentUseCase;

    //event sourcing:
    private final EnrollmentCommandHandler enrollmentCommandHandler;

    @PostMapping
    /*public ResponseEntity<Enrollment> createEnrollment(@RequestBody CreateEnrollmentRequest request) {
        Enrollment enrollment = enrollStudentUseCase.createEnrollment(
                request.getStudentId(),
                request.getCourseId(),
                request.getStudentEmail()
        );
        return ResponseEntity.ok(enrollment);
    }*/
    public ResponseEntity<EnrollmentResponse>
    enrollStudent(@RequestBody EnrollmentRequest request) {

        EnrollStudentCommand command
                = EnrollStudentCommand.builder()
                .studentId(request.getStudentId())
                //.studentName(request.getStudentName())
                .studentEmail(request.getStudentEmail())
                .courseId(request.getCourseId())
                .build();

        String enrollmentId = enrollmentCommandHandler.enrollStudent(command);

        return ResponseEntity.ok(new EnrollmentResponse(enrollmentId));
    }

    /**
     *  Agregar una lesson al curso
     *  Cada lesson agrega un 10% de progreso al curso.
     * @param enrollmentId
     * @param lessonId
     * @return
     */
    @PostMapping("/{enrollmentId}/lessons/{lessonId}")
    public ResponseEntity<Void> addLesson(@PathVariable String enrollmentId,
                                          @PathVariable String lessonId) {

        enrollmentCommandHandler.addLesson(enrollmentId, lessonId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{enrollmentId}/progress")
    public ResponseEntity<Void> getEnrollmentProgress(@PathVariable String enrollmentId) {
        // Lógica para obtener el progreso de la inscripción

        Enrollment enrollment = enrollmentCommandHandler.getEnrollment(enrollmentId);

        /*log.info("Enrollment {} - Current progress: {}%",
                enrollmentId, enrollment.getProgressPercentage());*/
        System.out.println("Enrollment {} - Current progress: {}%"+
                enrollmentId + enrollment.getProgressPercentage());

        return ResponseEntity.ok().build();
    }

    //Se debe realizar la siguiente tarea referente a Event Sourcing >
    // https://github.com/jgomezz/arq_m3_s3_eda_intro_lms/blob/features_3_event_source/TAREA.md
    /*Implementar un sistema de comentarios y rating para los cursos
        Requisitos Funcionales
        - Estudiantes dejan comentarios de un curso
        - Estudiante califican el curso con una puntuación de 1 a 5
        - Pueden editar los comentarios y ratings
        Requisitos Técnicos
        - Usar Event Sourcing para manejar comentarios y ratings
        Modulo a Implementar : Comentarios
        - CourseComment --> Agregate
        - Eventos
            + CommentAddedEvent
            + CommentEditedEvent
        - CommentCommandHandler
        - MemoryEventStore ( ya existe en el proyecto)
    */

}
