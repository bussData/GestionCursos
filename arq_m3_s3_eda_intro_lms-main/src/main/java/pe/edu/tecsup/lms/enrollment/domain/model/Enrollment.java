package pe.edu.tecsup.lms.enrollment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pe.edu.tecsup.lms.enrollment.domain.event.LessonCompletedEvent;
import pe.edu.tecsup.lms.enrollment.domain.event.StudentEnrolledEvent;
import pe.edu.tecsup.lms.shared.domain.event.DomainEvent;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {
    private Long id;

    private Long studentId;

    private Long courseId;

    private String studentEmail;

    private EnrollmentStatus status;

    private LocalDateTime enrolledAt;

    private int progressPercentage; //para Event Sourcing

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

    //Inicio para event Sourcing:
    public static Enrollment fromEvents(List<DomainEvent> events) {

        Enrollment enrollment = new Enrollment();

        for (DomainEvent event : events) {
            enrollment.apply(event);
        }
        return enrollment;
    }

    private void apply(DomainEvent event) {

        if (event instanceof StudentEnrolledEvent enrolledEvent) {
            this.id = Long.parseLong(enrolledEvent.getEnrollmentId());
            this.studentId = enrolledEvent.getStudentId();
            //this.studentName = enrolledEvent.getStudentName();
            this.studentEmail = enrolledEvent.getStudentEmail();
            this.courseId = enrolledEvent.getCourseId();
            //campos:
            this.status = enrolledEvent.getStatus();
            this.enrolledAt = enrolledEvent.getEnrolledAt();
        } else if (event instanceof  LessonCompletedEvent lessonCompletedEvent) {
                this.progressPercentage = lessonCompletedEvent.getNewProgressPercentage();
        } else if (event instanceof  DomainEvent  domainEvent) {
            // TO DO
        }


    }
}
