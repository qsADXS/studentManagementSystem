package com.example.studentmanagementsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    String id;
    String name;
    String password;
    int sex;
    int age;
    String phone;
    final int level = 3;
}
