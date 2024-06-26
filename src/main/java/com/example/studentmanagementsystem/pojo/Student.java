package com.example.studentmanagementsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/*
    * 学生实体类
    * 用于存储学生信息
    * 和数据库相同
 */
public class Student {
    Integer id;//学号
    String name;
    String password;
    final int level = 1;//学生的level为1
    Integer major_id;//专业id
    Integer sex;
    Integer age;
    String major;//专业名
}
