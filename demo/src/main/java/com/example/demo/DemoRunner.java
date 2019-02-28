package com.example.demo;

import com.example.demo.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DemoRunner implements ApplicationRunner {

    PersonService personService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        personService.savePerson();
    }
}
