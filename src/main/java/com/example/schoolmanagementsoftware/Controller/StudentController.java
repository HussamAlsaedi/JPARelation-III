package com.example.schoolmanagementsoftware.Controller;

import com.example.schoolmanagementsoftware.DTO.StudentDTO;
import com.example.schoolmanagementsoftware.Model.Student;
import com.example.schoolmanagementsoftware.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.type.ListType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity<List<Student>> getAllStudents() {

        return  ResponseEntity.status(200).body(studentService.getAllStudents());
    }

    @GetMapping("/get/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable Integer studentId) {
        Student student = studentService.getStudentById(studentId);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@RequestBody @Valid Student student) {
        studentService.addStudent(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestBody @Valid Student student) {
        studentService.updateStudent(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<Student> deleteStudent(@PathVariable int studentId) {
        studentService.deleteStudentById(studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/assignStudent/{studentId}/{courseId}")
    public ResponseEntity<Student> assignStudent(@PathVariable Integer studentId, @PathVariable Integer courseId) {
       studentService.assignStudent(studentId, courseId);
       return new ResponseEntity<>(studentService.getStudentById(studentId), HttpStatus.OK);

    }

    @GetMapping("/changeMajor/{studentId}/{Major}")
    public ResponseEntity<Student> changeMajor(@PathVariable Integer studentId, @PathVariable String Major) {
        studentService.changeMajor(studentId, Major);
        return new ResponseEntity<>(studentService.getStudentById(studentId), HttpStatus.OK);
    }

    @GetMapping("/getStudentsDTONameAndMajor")
    public ResponseEntity<List<StudentDTO>> getStudentsDTONameAndMajor() {
        List<StudentDTO> students = studentService.getStudentDTONameAndMajor();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}
