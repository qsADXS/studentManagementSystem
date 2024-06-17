package com.example.studentmanagementsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//上面是lombok的注解，用于自动生成getter、setter、toString等方法
/*
    * 学生实体类
    * 用于存储学生信息
    * 和数据库相同
 */
public class Student {
    String id;//学号
    String name;
    String password;
    final int level = 1;//学生的level为1
    int major_id;//专业id
}
