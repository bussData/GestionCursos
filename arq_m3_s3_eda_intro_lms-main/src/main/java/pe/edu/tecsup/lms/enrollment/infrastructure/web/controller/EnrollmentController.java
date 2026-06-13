package pe.edu.tecsup.lms.enrollment.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.tecsup.lms.enrollment.application.EnrollStudentUseCase;
import pe.edu.tecsup.lms.enrollment.domain.model.Enrollment;
import pe.edu.tecsup.lms.enrollment.infrastructure.web.dto.CreateEnrollmentRequest;
@RestController
@RequestMapping("/api/enrollment")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollStudentUseCase enrollStudentUseCase;

    @PostMapping
    public ResponseEntity<Enrollment> createEnrollment(@RequestBody CreateEnrollmentRequest request) {
        Enrollment enrollment = enrollStudentUseCase.createEnrollment(
                request.getStudentId(),
                request.getCourseId(),
                request.getStudentEmail()
        );
        return ResponseEntity.ok(enrollment);
    }

}
