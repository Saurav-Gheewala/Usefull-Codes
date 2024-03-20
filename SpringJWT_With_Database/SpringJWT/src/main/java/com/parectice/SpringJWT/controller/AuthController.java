package com.parectice.SpringJWT.controller;

import com.parectice.SpringJWT.model.JwtRequest;
import com.parectice.SpringJWT.model.JwtResponse;
import com.parectice.SpringJWT.model.User;
import com.parectice.SpringJWT.security.JwtHelper;
import com.parectice.SpringJWT.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
//http://localhost:8080/auth/login
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager manager;
    @Autowired
    JwtHelper helper;

    @Autowired
    UserService userService;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }
    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
    @PostMapping("/add")
    public User adduser(@RequestBody User user) {
        return userService.addUsers(user);
    }

}
