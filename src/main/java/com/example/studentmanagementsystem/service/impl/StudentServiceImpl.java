package com.example.studentmanagementsystem.service.impl;

import com.example.studentmanagementsystem.dao.StudentMapper;
import com.example.studentmanagementsystem.dto.StudentDTO;
import com.example.studentmanagementsystem.pojo.Student;
import com.example.studentmanagementsystem.service.inter.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j//这个是日志的注解
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;
    @Override
    public Student getStudentInfo(String id) {
        Student student = studentMapper.selectStudentInfo(id);
        return student;
    }
}
