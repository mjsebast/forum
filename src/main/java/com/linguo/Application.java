package com.linguo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.flywaydb.core.Flyway;

@EnableWebMvc
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) throws Throwable {
        Flyway flyway = new Flyway();
        flyway.setDataSource(System.getenv("BOXFUSE_DATABASE_URL"),
                System.getenv("BOXFUSE_DATABASE_USER"),
                System.getenv("BOXFUSE_DATABASE_PASSWORD"));
        flyway.migrate();
        SpringApplication.run(Application.class, args);
    }
}
