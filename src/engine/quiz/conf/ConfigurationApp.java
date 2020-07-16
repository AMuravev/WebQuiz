package engine.quiz.conf;

import engine.quiz.component.QuizCollection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationApp {

    @Bean
    public QuizCollection getQuizCollection() {
        return new QuizCollection();
    }
}
