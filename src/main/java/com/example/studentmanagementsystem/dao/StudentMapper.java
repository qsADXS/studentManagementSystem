package com.example.studentmanagementsystem.dao;


import com.example.studentmanagementsystem.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentMapper {
    @Select("select * from `studentmanagementsystem`.`student_info` where id=#{id}")
    Student selectStudentInfo(String id);

}
