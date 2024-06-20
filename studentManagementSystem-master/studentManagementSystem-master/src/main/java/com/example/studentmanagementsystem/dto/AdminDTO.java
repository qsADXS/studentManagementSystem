package com.example.studentmanagementsystem.dto;

import com.example.studentmanagementsystem.pojo.Admin;
import lombok.Data;

@Data
public class AdminDTO {
    String id;
    String name;
    int sex;
    int age;
    String phone;

    public AdminDTO(Admin admin){
        id = admin.getId();
        name = admin.getName();
        sex = admin.getSex();
        age = admin.getAge();
        phone = admin.getPhone();
    }
}
