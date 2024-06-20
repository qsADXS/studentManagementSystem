package com.example.studentmanagementsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//上面是lombok的注解，用于自动生成getter、setter、toString等方法
/*
    * 教师实体类
    * 用于存储学生信息
    * 和数据库相同
 */
public class Teacher {
    String id;//职工号
    String name;
    String password;
    int sex;
    int age;
    final int level = 2;//教师的level为1
    String title;//职称
    int college_id;//学院id
}
