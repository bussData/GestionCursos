package pe.edu.tecsup.lms.lesson.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import pe.edu.tecsup.lms.lesson.domain.model.Lesson;
import pe.edu.tecsup.lms.lesson.infrastructure.persistence.entity.LessonJpaEntity;

@Mapper(componentModel = "spring")
public interface LessonJpaMapper {


    LessonJpaEntity toEntity(Lesson lesson);

    Lesson toDomain(LessonJpaEntity entity);
}
