package com.example.studentmanagementsystem.service.inter;


import com.example.studentmanagementsystem.pojo.Student;

public interface StudentService {
    Student getStudentInfo(String id);
    Integer addCourse(Long aLong, Long aLong1);

    Integer delCourse(Long aLong, Long aLong1);

    Integer getCourseGrade(Long aLong, Long aLong1);



}
