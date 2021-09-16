package com.alianza.ptecnica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PtecnicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PtecnicaApplication.class, args);
    }

}
