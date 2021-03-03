package com.jocamav.springbootrest.start;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class PersonController {

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/person")
    public Person getPerson(@RequestParam(value = "name", defaultValue = "Jon") String name) {
        return new Person(counter.incrementAndGet(), name);
    }
}
