package com.example.studentmanagementsystem.dto;

import com.example.studentmanagementsystem.pojo.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//传递给前端的消息
public class TeacherDTO {
    Integer id;
    String name;
    Integer college_id;
    Integer sex;
    Integer age;
    String title ;
    String collegeName;
}
