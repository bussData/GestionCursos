package pe.edu.tecsup.lms.lesson.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import pe.edu.tecsup.lms.lesson.domain.model.Lesson;

import java.time.LocalDateTime;

@Entity
@Table(name = "lesson")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LessonJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "lesson_id", nullable = false)
    private Long lessonId;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Lesson.LessonStatus status;

    @Column(name = "completed_at", nullable = false)
    private LocalDateTime completedAt;

}
