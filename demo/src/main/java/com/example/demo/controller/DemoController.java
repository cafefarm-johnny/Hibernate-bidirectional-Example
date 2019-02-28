package com.example.demo.controller;

import com.example.demo.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@AllArgsConstructor
public class DemoController {

    PersonService service;

    @PostMapping("/push")
    public HashMap<String, Object> push(@RequestBody HashMap<String, Object> personMap) {

        return service.savePersonFromJSON(personMap);
    }
}
