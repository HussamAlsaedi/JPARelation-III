package com.example.schoolmanagementsoftware.Service;


import com.example.schoolmanagementsoftware.DTO.StudentDTO;
import com.example.schoolmanagementsoftware.Model.Course;
import com.example.schoolmanagementsoftware.Model.Student;
import com.example.schoolmanagementsoftware.Repostiroy.CourseRepository;
import com.example.schoolmanagementsoftware.Repostiroy.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class StudentService {


    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public Student getStudentById(Integer studentId) {
        return studentRepository.findById(studentId).orElseThrow();
    }

    public void updateStudent(Student  student) {
        Student stud = studentRepository.findById(student.getId()).orElseThrow();
        stud.setName(student.getName());
        stud.setAge(student.getAge());
        stud.setMajor(student.getMajor());

        studentRepository.save(stud);
    }

    public void deleteStudentById(Integer studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        studentRepository.deleteById(studentId);
    }


    public void assignStudent(Integer studentId, Integer courseId) {
        Student stud = studentRepository.findById(studentId).orElseThrow();
        Course course = courseRepository.findCourseByCourse_id(courseId);

        if (stud == null || course == null) {
            throw new RuntimeException("Student not found");
        }

        stud.getCourses().add(course);
        course.getStudents().add(stud);
        studentRepository.save(stud);
        courseRepository.save(course);

    }


    public void changeMajor(Integer studentId, String major) {
        Student stud = studentRepository.findById(studentId).orElseThrow();
        stud.setMajor(major);
        stud.setCourses(null);
        studentRepository.save(stud);

    }


    public List<StudentDTO> getStudentDTONameAndMajor() {
         List<Student> students = studentRepository.findAll();
          List<StudentDTO> studentDTOs = new ArrayList<>();

          for (Student student : students) {

              StudentDTO studentDTO = new StudentDTO(student.getName(), student.getMajor());
              studentDTOs.add(studentDTO);
          }

          return studentDTOs;
    }


}
