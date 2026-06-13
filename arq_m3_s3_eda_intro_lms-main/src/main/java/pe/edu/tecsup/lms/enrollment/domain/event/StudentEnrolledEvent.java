package pe.edu.tecsup.lms.enrollment.domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import pe.edu.tecsup.lms.enrollment.domain.model.Enrollment;
import pe.edu.tecsup.lms.shared.domain.event.DomainEvent;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
@Builder //necesario para los build()
public class StudentEnrolledEvent extends DomainEvent {
    private Long studentId;

    private Long courseId;

    private String studentEmail;

    private Enrollment.EnrollmentStatus status;

    private LocalDateTime enrolledAt;

    private String enrollmentId; //nuevo para Event Sourcing


}
