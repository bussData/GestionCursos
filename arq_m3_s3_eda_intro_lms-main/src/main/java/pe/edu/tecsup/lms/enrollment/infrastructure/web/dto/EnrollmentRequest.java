package pe.edu.tecsup.lms.enrollment.infrastructure.web.dto;
import lombok.Data;

@Data
public class EnrollmentRequest {
    private  String studentId;
    //private  String studentName;
    private  String courseId;
    private String studentEmail;
}