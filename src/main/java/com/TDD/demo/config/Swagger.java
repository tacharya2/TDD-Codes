package com.TDD.demo.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class Swagger {

        @Bean
        public GroupedOpenApi contactDetailsApi(){
                return GroupedOpenApi.builder()
                        .group("Contact Details API")
                        .packagesToScan("com.TDD.demo.controller")
                        .build();

        }
}
