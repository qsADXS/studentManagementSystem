package com.example.studentmanagementsystem.dao;


import com.example.studentmanagementsystem.pojo.Admin;
import com.example.studentmanagementsystem.pojo.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface StudentMapper {
    @Select("select * from `studentmanagementsystem`.`student_info` where id=#{id}")
    Student selectStudentInfo(String id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Transactional
    @Insert("INSERT INTO `student_info` (`name`, `sex`, `age`, `phone`) VALUES (#{name},#{sex},#{age},#{phone})")
    void insertStudent(Student student);

}
