package pe.edu.tecsup.lms.lesson.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.tecsup.lms.lesson.infrastructure.persistence.entity.LessonJpaEntity;

public interface JpaLessonRepository extends JpaRepository<LessonJpaEntity, Long> {
}
