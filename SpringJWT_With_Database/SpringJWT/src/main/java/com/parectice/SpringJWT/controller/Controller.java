package com.parectice.SpringJWT.controller;

import com.parectice.SpringJWT.model.User;
import com.parectice.SpringJWT.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    UserService userService;
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

    @GetMapping("/show")
    public ResponseEntity<List<User>> showUser() {
        return userService.showUsers();
    }
}
