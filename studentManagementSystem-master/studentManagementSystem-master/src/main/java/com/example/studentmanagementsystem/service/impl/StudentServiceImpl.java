package com.example.studentmanagementsystem.service.impl;

import com.example.studentmanagementsystem.dao.StudentMapper;
import com.example.studentmanagementsystem.pojo.Student;
import com.example.studentmanagementsystem.service.inter.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j//这个是日志的注解
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired//这个是springboot的注解，表示自动注入，反正用的时候把这两行复制就行
    StudentMapper studentMapper;
    @Override
    public Student getStudentInfo(String id) {
        Student student = studentMapper.selectStudentInfo(id);
        return student;
    }

    @Override
    public Integer addCourse(Long studentId, Long id) {
        return studentMapper.addCourse(studentId, id);
    }

    @Override
    public Integer delCourse(Long studentId, Long id) {
        return studentMapper.delCourse(studentId, id);
    }

    @Override
    public Integer getCourseGrade(Long studentId, Long id) {
        return studentMapper.getCourseGrade(studentId, id);
    }
}
