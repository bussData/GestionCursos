package pe.edu.tecsup.lms.lesson.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.lms.enrollment.domain.model.Enrollment;
import pe.edu.tecsup.lms.lesson.domain.model.Lesson;
import pe.edu.tecsup.lms.lesson.domain.repository.LessonRepository;
import pe.edu.tecsup.lms.lesson.infrastructure.persistence.entity.LessonJpaEntity;
import pe.edu.tecsup.lms.lesson.infrastructure.persistence.mapper.LessonJpaMapper;
import pe.edu.tecsup.lms.lesson.infrastructure.persistence.repository.JpaLessonRepository;

@Component
@RequiredArgsConstructor
public class LessonRepositoryAdapter implements LessonRepository {

    private final JpaLessonRepository jpaRepository;
    private final LessonJpaMapper mapper;

    @Override
    public Lesson save(Lesson lesson) {
        LessonJpaEntity entity = mapper.toEntity(lesson);
        LessonJpaEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }
}
