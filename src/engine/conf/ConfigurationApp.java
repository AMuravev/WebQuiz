package engine.conf;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

public class ConfigurationApp {
    @Bean
    public ModelMapper getDTOModelMapper() {
        return new ModelMapper();
    }
}
