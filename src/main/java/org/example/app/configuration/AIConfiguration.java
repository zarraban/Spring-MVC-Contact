package org.example.app.configuration;


import com.openai.client.OpenAIClient;

import com.openai.client.okhttp.OpenAIOkHttpClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Objects;

@Configuration
@ComponentScan("org.example.app")
@PropertySource("classpath:app.properties")
public class AIConfiguration {

    @Autowired
    Environment environment;

    @Bean
    public OpenAIClient openAIClient(){
        String apiKey = environment.getProperty("spring.ai.deepseek.api-key");
        return OpenAIOkHttpClient
                .builder()
                .apiKey(Objects.requireNonNull(apiKey))
                .baseUrl("https://api.deepseek.com")
                .build();

    }

}
