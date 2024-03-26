package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.model.Department;
import com.example.demo.model.Laboratory;
import com.example.demo.model.Teacher;
import com.example.demo.service.GenericService;
import com.example.demo.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class GenericController   {
    @Autowired
    GenericService genericService;
    @Autowired
    TimetableService timetableService;
    @PostMapping("/departments")
    public ResponseEntity<String> saveDepartment(@RequestBody Department department) {
        return genericService.saveDepartment(department);
    }

    @PostMapping("/courses")
    public ResponseEntity<String> saveCourse(@RequestBody Course course) {
        return genericService.saveCourse(course);
    }

    @PostMapping("/laboratories")
    public ResponseEntity<String> saveLaboratory(@RequestBody Laboratory laboratory) {
        return genericService.saveLaboratory(laboratory);
    }

    @PostMapping("/teachers")
    public ResponseEntity<String> saveTeacher(@RequestBody Teacher teacher) {
        return genericService.saveTeacher(teacher);
    }

    @GetMapping("/time")
    public List<String> generateTimetable()
    {
        return timetableService.createTimetable();
    }

}
