package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.Department;
import com.example.demo.model.Laboratory;
import com.example.demo.model.Teacher;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.LaboratoryRepository;
import com.example.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GenericService {

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    LaboratoryRepository laboratoryRepository;
    @Autowired
    TeacherRepository teacherRepository;

    public ResponseEntity<String> saveDepartment(Department department) {
        Department savedDepartment = departmentRepository.save(department);
        return ResponseEntity.ok("Department saved with ID: " + savedDepartment.getId());
    }

    public ResponseEntity<String> saveCourse(Course course) {
        Course savedCourse = courseRepository.save(course);
        return ResponseEntity.ok("Course saved with ID: " + savedCourse.getId());
    }

    public ResponseEntity<String> saveLaboratory(Laboratory laboratory) {
        Laboratory savedLaboratory = laboratoryRepository.save(laboratory);
        return ResponseEntity.ok("Laboratory saved with ID: " + savedLaboratory.getId());
    }

    public ResponseEntity<String> saveTeacher(Teacher teacher) {
        Teacher savedTeacher = teacherRepository.save(teacher);
        return ResponseEntity.ok("Teacher saved with ID: " + savedTeacher.getId());
    }
}
