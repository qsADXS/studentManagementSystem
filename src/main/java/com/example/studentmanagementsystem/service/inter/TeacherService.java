package com.example.studentmanagementsystem.service.inter;


import com.example.studentmanagementsystem.pojo.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher getTeacherInfo(String id);

    Integer setGrade(String teacherId, String crouseId, String studentId, Integer grade);

    List<Long> getCourseIds(String teacherId);
}
