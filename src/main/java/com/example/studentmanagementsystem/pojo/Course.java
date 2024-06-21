package com.example.studentmanagementsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    Integer id;
    String name;
    Integer credit;
    Integer teacher_id;
}
