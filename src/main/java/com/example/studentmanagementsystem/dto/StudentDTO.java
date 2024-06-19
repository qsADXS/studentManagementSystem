package com.example.studentmanagementsystem.dto;

import com.example.studentmanagementsystem.pojo.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//传递给前端的消息
public class StudentDTO {
    String id;
    String name;
    int major_id;
    int sex;
    int age;
    //todo 修改
    String major = "计算机科学与技术";
    public StudentDTO(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.major_id = student.getMajor_id();
        this.age = student.getAge();
        this.sex = student.getSex();
    }
}
