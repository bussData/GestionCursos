package pe.edu.tecsup.lms.lesson.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.tecsup.lms.lesson.application.LessonUseCase;
import pe.edu.tecsup.lms.lesson.application.LessonUseCaseImpl;
import pe.edu.tecsup.lms.lesson.domain.repository.LessonRepository;
import pe.edu.tecsup.lms.shared.domain.event.EventPublisher;


@Configuration
public class LessonBeanConfiguration {

    @Bean
    public LessonUseCase createLessonUseCase(LessonRepository repository, EventPublisher eventPublisher) {

        return new LessonUseCaseImpl( repository, eventPublisher);
    }
}
