package com.example.studentmanagementsystem.dao;

import com.example.studentmanagementsystem.pojo.Admin;
import com.example.studentmanagementsystem.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {
    @Select("select * from `studentmanagementsystem`.`admin_info` where id=#{id}")
    Admin selectStudentInfo(String id);
}
