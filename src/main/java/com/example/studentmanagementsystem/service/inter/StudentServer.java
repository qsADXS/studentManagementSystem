package com.example.studentmanagementsystem.service.inter;


import com.example.studentmanagementsystem.dto.CourseDTO;
import com.example.studentmanagementsystem.pojo.Student;

import java.util.List;
import java.util.Map;

public interface StudentServer {
    Student getStudentInfo(String id);
    Integer addCourse(Long aLong, Long aLong1);

    Integer delCourse(Long aLong, Long aLong1);

    Integer getCourseGrade(Long aLong, Long aLong1);

    List<Map<String, Object>> getAllCourseGrade(Long aLong);

    List<Map<String, Object>> getTeacherCourseGrade(List<Long> courseIds, Long aLong);

    List<Student> getAllStudentInfo();
    void updatePassword(Integer id, String password, String newPassword);
    List<CourseDTO> getAllCourse(Integer id);
    void updateInfo(Student student);

}
