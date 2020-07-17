package engine.conf;

import engine.repository.QuizRepository;
import engine.service.QuizCheckService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationApp {

    @Bean
    public QuizRepository getQuizRepository() {
        return new QuizRepository();
    }

    @Bean
    public QuizCheckService getQuizCheckService() {
        return new QuizCheckService();
    }
}