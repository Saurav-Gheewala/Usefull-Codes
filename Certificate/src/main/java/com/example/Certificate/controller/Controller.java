package com.example.Certificate.controller;

import com.example.Certificate.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class Controller {
    @Autowired
    Service service;
    @PostMapping("/upload")
    public ResponseEntity<String> start(@RequestParam("file")MultipartFile file) throws Exception
    {
        return service.process(file);
    }
}
