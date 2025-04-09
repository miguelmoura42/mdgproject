package mdg.miguel.mdgproject.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class DateConfig {

  @Bean
  public ObjectMapper objectMapper() {

    ObjectMapper objectMapper = new ObjectMapper();
    JavaTimeModule javaTimeModule = new JavaTimeModule();

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDateSerializer localDateSerializer = new LocalDateSerializer(dateFormatter);
    LocalDateDeserializer localDateDeserializer = new LocalDateDeserializer(dateFormatter);

    javaTimeModule.addSerializer(localDateSerializer);
    javaTimeModule.addDeserializer(LocalDate.class, localDateDeserializer);

    objectMapper.registerModule(javaTimeModule);
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    return objectMapper;
  }
}