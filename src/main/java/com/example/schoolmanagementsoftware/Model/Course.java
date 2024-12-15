package com.example.schoolmanagementsoftware.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
    public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int course_id;

    @Column(nullable = false)
    @NotEmpty(message = "name is mandatory")
    private String name;



    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private Set<Student> students;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;
}
