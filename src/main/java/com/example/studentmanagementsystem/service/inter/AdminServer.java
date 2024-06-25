package com.example.studentmanagementsystem.service.inter;

import com.example.studentmanagementsystem.dto.StudentDTO;
import com.example.studentmanagementsystem.dto.TeacherDTO;
import com.example.studentmanagementsystem.pojo.Admin;
import com.example.studentmanagementsystem.pojo.Course;
import com.example.studentmanagementsystem.pojo.Student;
import com.example.studentmanagementsystem.pojo.Teacher;

import java.util.List;


public interface AdminServer {
    Admin getAdminInfo(Integer id);
    Integer setAdminPhone(Integer id, String phone);
    Integer insertAdmin(Admin admin);
    Integer insertTeacher(Teacher teacher);
    Integer insertStudent(Student student);
    Integer resetPassword(Integer level, Integer id);
    Integer insertCourse(Course course);
    Integer deleteCourse(Integer id);
    Integer updateTeacherTitle(Teacher teacher);
    List<StudentDTO> getStudentList(Integer page, Integer limit);
    List<TeacherDTO> getTeacherList(Integer page, Integer limit);
    void updatePassword(Integer id, String password, String newPassword);

}
