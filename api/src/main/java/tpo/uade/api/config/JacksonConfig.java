package tpo.uade.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {

        ObjectMapper objectMapper = new ObjectMapper();
        // Configura la estrategia de nombres para que convierta camelCase a snake_case
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        // Añadir soporte para fechas y horas de Java 8 (LocalDate, LocalDateTime, etc.)
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
}

