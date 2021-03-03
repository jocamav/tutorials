package com.jocamav.springbootrest.http;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootRestHttpMethodsApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringBootRestHttpMethodsApplication.class, args);
        PersonService personService = ctx.getBean(PersonService.class);
        personService.save(new Person("Jon", "Snow"));
        personService.save(new Person("Sansa", "Stark"));
        personService.save(new Person("Jaime", "Lannister"));
    }

}
