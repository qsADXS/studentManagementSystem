package com.example.studentmanagementsystem.dao;

import com.example.studentmanagementsystem.pojo.Admin;
import com.example.studentmanagementsystem.pojo.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface AdminMapper {
    @Select("select * from admin_info where id=#{id}")
    Admin selectStudentInfo(String id);
    @Update("UPDATE `admin_info` SET `phone` = #{phone} WHERE `id` = #{id}")
    @Transactional
    Integer updateAdminPhone(String id, String phone);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Transactional
    @Insert("INSERT INTO `admin_info` (`name`, `sex`, `age`, `phone`) VALUES (#{name},#{sex},#{age},#{phone})")
    void insertAdmin(Admin admin);
}
