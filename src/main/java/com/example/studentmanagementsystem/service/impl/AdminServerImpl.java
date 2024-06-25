package com.example.studentmanagementsystem.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.example.studentmanagementsystem.common.ErrorEnum;
import com.example.studentmanagementsystem.component.DefinitionException;
import com.example.studentmanagementsystem.dao.*;
import com.example.studentmanagementsystem.dto.StudentDTO;
import com.example.studentmanagementsystem.dto.TeacherDTO;
import com.example.studentmanagementsystem.pojo.Admin;
import com.example.studentmanagementsystem.pojo.Course;
import com.example.studentmanagementsystem.pojo.Student;
import com.example.studentmanagementsystem.pojo.Teacher;
import com.example.studentmanagementsystem.service.inter.AdminServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminServerImpl implements AdminServer {
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    CollegeMapper collegeMapper;
    @Autowired
    MajorMapper majorMapper;
    @Override
    public Admin getAdminInfo(Integer id) {
        return adminMapper.selectStudentInfo(id);
    }

    @Override
    public Integer setAdminPhone(Integer id, String phone) {
        return adminMapper.updateAdminPhone(id, phone);
    }

    @Override
    public Integer insertAdmin(Admin admin) {
        adminMapper.insertAdmin(admin);
        return admin.getId();
    }

    @Override
    @Transactional
    public Integer insertTeacher(Teacher teacher) {
        if(collegeMapper.selectCollegeInfo(teacher.getCollege_id()) == null){
            throw new DefinitionException(ErrorEnum.ERROR);
        }
        adminMapper.insertTeacher(teacher);
        return Integer.valueOf(teacher.getId());
    }

    @Override
    @Transactional
    public Integer insertStudent(Student student) {
        if(majorMapper.selectMajorInfo(student.getMajor_id()) == null){
            throw new DefinitionException(ErrorEnum.ERROR);
        }
        adminMapper.insertStudent(student);
        return Integer.valueOf(student.getId());
    }

    @Override
    public Integer resetPassword(Integer level, Integer id) {
        String password = SecureUtil.md5("123456");
        if(level == 1) {
            return adminMapper.updateStudentPassword(id, password);
        }else if(level == 2){
            return adminMapper.updateTeacherPassword(id, password);
        }else{
            throw new DefinitionException(ErrorEnum.ERROR);
        }
    }

    @Override
    public Integer insertCourse(Course course) {
        if(teacherMapper.getTeacherInfo(String.valueOf(course.getTeacher_id())) == null){
            throw new DefinitionException(ErrorEnum.ERROR);
        }
        courseMapper.insertMajor(course);
        return course.getId();
    }

    @Override
    @Transactional
    public Integer deleteCourse(Integer id) {
        courseMapper.deleteChooseCourse(id);
        return courseMapper.deleteCourse(id);
    }

    @Override
    public Integer updateTeacherTitle(Teacher teacher) {
        return adminMapper.updateTeacherTitle(teacher);
    }

    @Override
    public List<StudentDTO> getStudentList(Integer page, Integer limit) {
        int offset = (page - 1) * limit;
        return adminMapper.selectStudentList(offset, limit);
    }

    @Override
    public List<TeacherDTO> getTeacherList(Integer page, Integer limit) {
        int offset = (page - 1) * limit;
        return adminMapper.selectTeacherLister(offset,limit);
    }
    @Override
    public void updatePassword(Integer id, String password, String newPassword) {
        newPassword = SecureUtil.md5(newPassword);
        adminMapper.updatePassword(id, password, newPassword);
    }
}
