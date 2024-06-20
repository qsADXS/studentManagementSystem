package com.example.studentmanagementsystem;


import com.example.studentmanagementsystem.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.studentmanagementsystem.util.JwtUtils;

@SpringBootTest
class StudentManagementSystemApplicationTests {
    @Autowired
    StudentServiceImpl studentService;

    @Test
    void contextLoads() {
        String s = JwtUtils.generateToken("1", 1);
        System.out.println(JwtUtils.getClaims(s));
    }

}
