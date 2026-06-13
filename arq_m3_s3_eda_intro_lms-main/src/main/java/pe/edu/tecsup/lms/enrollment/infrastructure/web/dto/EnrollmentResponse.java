package pe.edu.tecsup.lms.enrollment.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnrollmentResponse {
    private String enrollmentId;
    private Long studentId;
    private Long courseId;
    private String studentEmail;

    public EnrollmentResponse(String enrollmentId) {
        this.setEnrollmentId(enrollmentId);
    }
}
