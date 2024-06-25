package com.example.studentmanagementsystem.service.inter;


import com.example.studentmanagementsystem.pojo.Teacher;

import java.util.List;

public interface TeacherServer {

    Teacher getTeacherInfo(String id);

    Integer setGrade(String teacherId, String crouseId, String studentId, Integer grade);

    List<Long> getCourseIds(String teacherId);
    void updatePassword(Integer id, String password, String newPassword);
}
