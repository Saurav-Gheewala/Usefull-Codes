package com.parectice.SpringJWT.controller;

import com.parectice.SpringJWT.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {
    Logger logger = LoggerFactory.getLogger(Controller.class);
    @GetMapping("/demo")
    public String message()
    {
        return "Hello World";
    }
    @GetMapping("/Current")
    public String getName(Principal principal)
    {
        return principal.getName();
    }
}
