package com.example.studentmanagementsystem.pojo;

import com.example.studentmanagementsystem.dto.AdminDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    Integer id;
    String name;
    String password;
    int sex;
    int age;
    String phone;
    final int level = 3;
    public Admin(AdminDTO adminDTO){
        name = adminDTO.getName();
        sex = adminDTO.getSex() == 0 ? 0 : 1;
        age = adminDTO.getAge();
        phone = adminDTO.getPhone();
        password = "123456";
    }
}
