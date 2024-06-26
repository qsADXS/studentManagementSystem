package com.example.studentmanagementsystem.dto;

import com.example.studentmanagementsystem.pojo.Admin;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminDTO {
    Integer id;
    String name;
    Integer sex;
    Integer age;
    String phone;

    public AdminDTO(Admin admin){
        id = admin.getId();
        name = admin.getName();
        sex = admin.getSex();
        age = admin.getAge();
        phone = admin.getPhone();
    }
}
