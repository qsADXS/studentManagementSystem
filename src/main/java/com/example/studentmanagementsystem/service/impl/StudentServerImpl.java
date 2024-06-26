package com.example.studentmanagementsystem.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.example.studentmanagementsystem.common.ErrorEnum;
import com.example.studentmanagementsystem.component.DefinitionException;
import com.example.studentmanagementsystem.dao.CourseMapper;
import com.example.studentmanagementsystem.dao.StudentMapper;
import com.example.studentmanagementsystem.dto.CourseDTO;
import com.example.studentmanagementsystem.pojo.Student;
import com.example.studentmanagementsystem.service.inter.StudentServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j//这个是日志的注解
@Service
public class StudentServerImpl implements StudentServer {
    @Autowired//这个是springboot的注解，表示自动注入，反正用的时候把这两行复制就行
    StudentMapper studentMapper;
    @Autowired
    MajorServerImpl majorServer;
    @Autowired
    CourseMapper courseMapper;
    @Override
    public Student getStudentInfo(String id) {
        Student student = studentMapper.selectStudentInfo(id);
        if(student == null){
            throw new DefinitionException(ErrorEnum.ERROR);
        }
        student.setMajor(majorServer.getMajorInfo(student.getMajor_id()).getName());
        return student;
    }
    @Override
    public Integer addCourse(Long studentId, Long id) {
        Integer count = courseMapper.selectCourseCount(Math.toIntExact(id));
        Integer maxCount = courseMapper.selectMaxCount(Math.toIntExact(id));
        log.info("count:{}",count);
        log.info("maxCount:{}",maxCount);
        if(count >= maxCount){
            throw new DefinitionException(ErrorEnum.ERROR);
        }
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
    @Override
    public List<Map<String, Object>> getAllCourseGrade(Long aLong) {

        return studentMapper.getAllCourseGrade(aLong);
    }

    @Override
    public List<Map<String, Object>> getTeacherCourseGrade(List<Long> courseIds, Long aLong) {

        return studentMapper.getTeacherCourseGrade(courseIds, aLong);
    }

    @Override
    public List<Student> getAllStudentInfo() {
        return studentMapper.getAllStudentInfo();
    }

    @Override
    public void updatePassword(Integer id, String password, String newPassword) {
        newPassword = SecureUtil.md5(newPassword);
        studentMapper.updatePassword(id,password,newPassword);
    }

    @Override
    public List<CourseDTO> getAllCourse(Integer id) {
        return courseMapper.selectCourseInfo(id);
    }

    @Override
    public void updateInfo(Student student) {
        studentMapper.updateStudentInfo(student);
    }

    @Override
    public Integer getMax(Integer id) {
        return courseMapper.maxGrade(id);
    }

    @Override
    public Integer getMin(Integer id) {
        return courseMapper.minGrade(id);
    }

    @Override
    public double getAvg(Integer id) {
        try{
            return courseMapper.avgGrade(id);
        }catch (Exception e){
            return 0;
        }
    }
}
