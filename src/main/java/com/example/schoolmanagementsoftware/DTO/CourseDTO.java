package com.example.schoolmanagementsoftware.DTO;

import com.example.schoolmanagementsoftware.Model.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseDTO {

    private int course_id;

    private String name;

    private Teacher teacher;
}
