package pe.edu.tecsup.lms.enrollment.infrastructure.web.dto;


import lombok.Data;

@Data
public class CreateEnrollmentRequest {

    private Long studentId;

    private Long courseId;

    private String studentEmail;
}
