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
    Integer id;
    String name;
    Integer major_id;
    Integer sex;
    Integer age;
    String major;
    public StudentDTO(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.major_id = student.getMajor_id();
        this.age = student.getAge();
        this.sex = student.getSex();
        if (student.getMajor() != null)
            this.major = student.getMajor();
    }
}
