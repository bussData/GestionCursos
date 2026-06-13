package pe.edu.tecsup.lms.enrollment.domain.model;

import org.junit.jupiter.api.Test;
import pe.edu.tecsup.lms.enrollment.domain.event.LessonCompletedEvent;
import pe.edu.tecsup.lms.enrollment.domain.event.StudentEnrolledEvent;
import pe.edu.tecsup.lms.shared.domain.event.DomainEvent;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EnrollmentTest {
    @Test
    void testEnrollmentCreation() {

        StudentEnrolledEvent event
                = StudentEnrolledEvent.builder()
                .enrollmentId("enroll-100")
                .courseId(Long.parseLong("01"))
                .studentId(Long.parseLong("011"))
                //.studentName("Juan")
                .build();

        List<DomainEvent> events = List.of(event);

        Enrollment enrollment = Enrollment.fromEvents(events);

        assertEquals("enroll-100", enrollment.getId());
        assertEquals("student-01", enrollment.getStudentId());
        assertEquals("course-01", enrollment.getCourseId());

    }

    /**
     *  Crear la matricula
     *  Ir aumentando el porcentaje de desarrollo del curso.
     *
     */
    @Test
    void testEnrollmentLessonProgressUpdate() {

        StudentEnrolledEvent event1
                = StudentEnrolledEvent.builder()
                .enrollmentId("enroll-100")
                .courseId(Long.parseLong("01"))
                .studentId(Long.parseLong("011"))
                //.studentName("Juan")
                .build();

        LessonCompletedEvent event2
                = LessonCompletedEvent.builder()
                .enrollmentId("enroll-100")
                .lessonId("lesson-01")
                .newProgressPercentage(25)
                .build();

        LessonCompletedEvent event3
                = LessonCompletedEvent.builder()
                .enrollmentId("enroll-100")
                .lessonId("lesson-01")
                .newProgressPercentage(60)
                .build();


        List<DomainEvent> events = List.of(event1, event2, event3);

        Enrollment enrollment = Enrollment.fromEvents(events);

        assertEquals("enroll-100", enrollment.getId());
        assertEquals("student-01", enrollment.getStudentId());
        assertEquals("course-01", enrollment.getCourseId());
        assertEquals(60, enrollment.getProgressPercentage());
    }
}
