package com.tenniscourts.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
@ComponentScan(basePackages = {"com.tenniscourts","com.tenniscourts.audit","com.tenniscourts.config","com.tenniscourts.config.persistence","com.tenniscourts.exceptions","com.tenniscourts.guests","com.tenniscourts.reservations","com.tenniscourts.schedules","com.tenniscourts.tenniscourts","com.tenniscourts.guests"})
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.tenniscourts"))
                .paths(PathSelectors.any())
                .build();
    }
}
