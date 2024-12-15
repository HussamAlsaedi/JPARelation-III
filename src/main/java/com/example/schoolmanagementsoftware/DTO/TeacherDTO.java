package com.example.schoolmanagementsoftware.DTO;


import com.example.schoolmanagementsoftware.Model.Course;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class TeacherDTO {


    private String name;
    private String email;
    private Set<Course> Courses;

}


