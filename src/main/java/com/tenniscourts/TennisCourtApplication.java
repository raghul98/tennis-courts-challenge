package com.tenniscourts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaAuditing
@EnableSwagger2
//@ComponentScan(basePackages = {"com.tenniscourts","com.tenniscourts.audit","com.tenniscourts.config","com.tenniscourts.config.persistence","com.tenniscourts.exceptions","com.tenniscourts.guests","com.tenniscourts.reservations","com.tenniscourts.schedules","com.tenniscourts.tenniscourts","com.tenniscourts.guests"})
public class TennisCourtApplication {

    public static void main(String[] args) {
        SpringApplication.run(TennisCourtApplication.class, args);
    }

}