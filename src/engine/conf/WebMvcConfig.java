package engine.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import engine.Utils.DTOModelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManager;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;
    private final EntityManager entityManager;
    private final ModelMapper modelMapper;

    @Autowired
    public WebMvcConfig(ApplicationContext applicationContext, EntityManager entityManager, ModelMapper modelMapper) {
        this.applicationContext = applicationContext;
        this.entityManager = entityManager;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().applicationContext(this.applicationContext).build();
        argumentResolvers.add(new DTOModelMapper(objectMapper, modelMapper));
    }
}
