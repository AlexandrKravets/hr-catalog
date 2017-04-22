package edu.alex.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @RequestMapping(value = "/health", produces = MediaType.TEXT_PLAIN_VALUE)
    public String health() {
        return "OK";
    }
}
