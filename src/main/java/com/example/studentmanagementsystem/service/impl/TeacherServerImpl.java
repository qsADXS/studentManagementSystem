package com.example.studentmanagementsystem.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.crypto.SecureUtil;
import com.example.studentmanagementsystem.dao.TeacherMapper;
import com.example.studentmanagementsystem.pojo.Teacher;
import com.example.studentmanagementsystem.service.inter.TeacherServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j//这个是日志的注解
@Service
public class TeacherServerImpl implements TeacherServer {
    @Autowired//这个是springboot的注解，表示自动注入，反正用的时候把这两行复制就行
    TeacherMapper teacherMapper;

    @Override
    public Teacher getTeacherInfo(String id) {
        return teacherMapper.getTeacherInfo(id);
    }

    @Override
    public Integer setGrade(String teacherId, String crouseId, String studentId, Integer grade) {
        return teacherMapper.setGrade(teacherId, Convert.toLong(crouseId) ,Convert.toLong (studentId), grade);
    }

    @Override
    public List<Long> getCourseIds(String teacherId) {
        return teacherMapper.getCourseIds(Convert.toInt(teacherId) );
    }

    @Override
    public void updatePassword(Integer id, String password, String newPassword) {
        newPassword = SecureUtil.md5(newPassword);
        teacherMapper.updatePassword(id, password, newPassword);
    }

    @Override
    public void updateInfo(Teacher teacher) {
        teacherMapper.updateTeacherInfo(teacher);
    }

}
