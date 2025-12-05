package com.aditya.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine; // Use 6 for Spring Boot 3+
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;

@Configuration
public class ThymeleafConfiguration {

    @Bean
    public TemplateEngine stringTemplateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();

        StringTemplateResolver stringResolver = new StringTemplateResolver();
        stringResolver.setTemplateMode(TemplateMode.HTML);
        stringResolver.setOrder(1);

        templateEngine.addTemplateResolver(stringResolver);
        return templateEngine;
    }
}
