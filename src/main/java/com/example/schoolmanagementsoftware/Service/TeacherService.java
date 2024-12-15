package com.example.schoolmanagementsoftware.Service;

import com.example.schoolmanagementsoftware.DTO.TeacherDTO;
import com.example.schoolmanagementsoftware.Model.Address;
import com.example.schoolmanagementsoftware.Model.Course;
import com.example.schoolmanagementsoftware.Model.Teacher;
import com.example.schoolmanagementsoftware.Repostiroy.AddressRepository;
import com.example.schoolmanagementsoftware.Repostiroy.CourseRepository;
import com.example.schoolmanagementsoftware.Repostiroy.TeacherRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final AddressRepository addressRepository;
    private final CourseRepository courseRepository;

    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }

    public List<TeacherDTO> findAllTeacherDTOs() {
        List<Teacher> teach = teacherRepository.findAll();
        List<TeacherDTO> teacherDTOs = new ArrayList<>();
        for (Teacher teacher : teach) {
            TeacherDTO teacherDTO = new TeacherDTO(teacher.getName(),teacher.getEmail(),teacher.getCourses());

            teacherDTOs.add(teacherDTO);
        }
        return teacherDTOs;
    }

    public Teacher findTeacherById(Integer teacherId) {

        return teacherRepository.findTeacherById(teacherId);
    }

    public void  addTeacher(@RequestBody @Valid Teacher teacher) {
        teacherRepository.save(teacher);

    }

    public void updateTeacher(Integer id, Teacher teacher) {
     Teacher teach = teacherRepository.findTeacherById(id);
        if (teach == null) {
            throw new RuntimeException("Customer not found");
        }
        teacherRepository.save(teacher);
    }

    public void deleteTeacher(Integer id) {

        Teacher teach = teacherRepository.findTeacherById(id);
        if (teach == null) {
            throw new RuntimeException("Teacher not found");
        }

        Address address = addressRepository.findAddressById(id);
        if (address == null) {
            throw new RuntimeException("Address not found");
        }
        teach.setAddress(null);
        addressRepository.delete(address);
        teacherRepository.delete(teach);

    }


    public void assignTeacher(Integer teacherId, Integer courseId) {
        Teacher teacher = teacherRepository.findTeacherById(teacherId);
        Course course = courseRepository.findCourseByCourse_id(courseId);

         if (teacher == null ) {
             throw new RuntimeException("Teacher not found");
         }

         if (course == null) {
             throw new RuntimeException("Course not found");
         }

        teacher.getCourses().add(course);

         teacherRepository.save(teacher);


    }
}
