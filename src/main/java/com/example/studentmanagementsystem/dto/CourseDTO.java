package com.example.studentmanagementsystem.dto;

import com.example.studentmanagementsystem.pojo.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    Integer id;
    Integer grade;
    String name;
    Integer count;
}
