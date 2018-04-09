package com.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.logic.ProjectConstants.*;
//TODO: email
//автоматическая - например, когда добавляется детектив
//по времени - например, исткает пароль у вас, надо обновить
@Configuration
@EnableWebMvc
public class MVCConfig extends WebMvcConfigurerAdapter {
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        final Map<String, String> parameterMap = new HashMap<String, String>();
        parameterMap.put("charset", "utf-8");
        configurer
                .defaultContentType(new MediaType(MediaType.APPLICATION_JSON, parameterMap));
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }//TODO allowOrigins

    @Bean
    public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()
                .addSerializer(LocalDate.class, new LocalDateSerializer(JSON_FORMATTER_DATE))
                .addSerializer(LocalTime.class, new LocalTimeSerializer(JSON_FORMATTER_TIME))
                .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(JSON_FORMATTER_DATETIME))
                .addDeserializer(LocalDate.class, new LocalDateDeserializer(JSON_FORMATTER_DATE))
                .addDeserializer(LocalTime.class, new LocalTimeDeserializer(JSON_FORMATTER_TIME))
                .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(JSON_FORMATTER_DATETIME)));
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(customJackson2HttpMessageConverter());
        converters.add(new ResourceHttpMessageConverter());
        super.configureMessageConverters(converters);
    }
}

