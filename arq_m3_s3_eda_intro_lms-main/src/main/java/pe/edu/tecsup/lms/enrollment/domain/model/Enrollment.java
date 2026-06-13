package pe.edu.tecsup.lms.enrollment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {
    private Long id;

    private Long studentId;

    private Long courseId;

    private String studentEmail;

    private EnrollmentStatus status;

    private LocalDateTime enrolledAt;

    public static Enrollment create(
            Long studentId,
            Long courseId,
            String studentEmail) {

        if (studentId == null) {
            throw new IllegalArgumentException(
                    "studentId is required");
        }

        if (courseId == null) {
            throw new IllegalArgumentException(
                    "courseId is required");
        }

        if (studentEmail == null || studentEmail.isBlank()) {
            throw new IllegalArgumentException(
                    "studentEmail is required");
        }

        Enrollment enrollment = new Enrollment();

        enrollment.studentId = studentId;
        enrollment.courseId = courseId;
        enrollment.studentEmail = studentEmail;
        enrollment.status = EnrollmentStatus.ACTIVE;
        enrollment.enrolledAt = LocalDateTime.now();

        return enrollment;
    }

    public void complete() {

        if (status != EnrollmentStatus.ACTIVE) {
            throw new IllegalStateException(
                    "Only ACTIVE enrollments can be completed. Current status: "
                            + status);
        }

        this.status = EnrollmentStatus.COMPLETED;
    }

    public void cancel() {

        if (status == EnrollmentStatus.COMPLETED) {
            throw new IllegalStateException(
                    "Completed enrollments cannot be cancelled");
        }

        this.status = EnrollmentStatus.CANCELLED;
    }

    public enum EnrollmentStatus {
        ACTIVE,
        COMPLETED,
        CANCELLED
    }
}
