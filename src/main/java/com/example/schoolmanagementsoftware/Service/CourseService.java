package com.example.schoolmanagementsoftware.Service;

import com.example.schoolmanagementsoftware.DTO.CourseDTO;
import com.example.schoolmanagementsoftware.Model.Course;
import com.example.schoolmanagementsoftware.Model.Student;
import com.example.schoolmanagementsoftware.Model.Teacher;
import com.example.schoolmanagementsoftware.Repostiroy.CourseRepository;
import com.example.schoolmanagementsoftware.Repostiroy.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    @Autowired
    private final TeacherRepository teacherRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public void addCourse(Integer teacherId, Course course) {
        Teacher teacher = teacherRepository.findTeacherById(teacherId);
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher not found");
        }
        course.setTeacher(teacher);
        courseRepository.save(course);
    }

    public void updateCourse(Integer teacherId, Integer courseId, Course course) {
      Teacher teacher = teacherRepository.findTeacherById(teacherId);
        Course course1 = courseRepository.findCourseByCourse_id(courseId);
        if (course1 == null && teacher == null) {
          throw new IllegalArgumentException("Course not found");
        }
        course1.setCourse_id(courseId);
        course1.setName(course.getName());
        course1.setTeacher(teacher);
        courseRepository.save(course1);

    }

    public void deleteCourse(Integer teacherId, Integer courseId) {
        Teacher teacher = teacherRepository.findTeacherById(teacherId);
        Course course1 = courseRepository.findCourseByCourse_id(courseId);
        if (course1 == null && teacher == null) {
            throw new IllegalArgumentException("Course not found");
        }
            course1.setTeacher(null);
        courseRepository.delete(course1);
    }


    public String getTeacherNameByCourseId(Integer courseId) {
        Course course = courseRepository.findCourseByCourse_id(courseId);
        if (course == null || course.getTeacher() == null) {
            throw new RuntimeException("Course or Teacher not found.");
        }
        return course.getTeacher().getName();
    }

    public Set<Student>  getStudentByCourseId(Integer courseId) {

        Course course = courseRepository.findCourseByCourse_id(courseId);
        if (course == null || course.getTeacher() == null) {
            throw new RuntimeException("Course or Teacher not found.");
        }

        return course.getStudents();
    }

    public List<CourseDTO> getCoursesIdAndNameAndTeacher() {
        List<Course> courses = courseRepository.findAll();

        List<CourseDTO> coursesDTO = new ArrayList<>();

        for (Course cou: courses) {
            CourseDTO courseDTO = new CourseDTO(cou.getCourse_id(), cou.getName(), cou.getTeacher());
            coursesDTO.add(courseDTO);
        }

        return coursesDTO;
    }

}
