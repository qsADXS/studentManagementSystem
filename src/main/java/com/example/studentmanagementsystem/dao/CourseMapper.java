package com.example.studentmanagementsystem.dao;


import com.example.studentmanagementsystem.pojo.Course;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface CourseMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Transactional
    @Insert("INSERT INTO `course_info` (`name`,`credit`,`teacher_id`) VALUES (#{name},#{credit},#{teacher_id});")
    void insertMajor(Course course);

    @Transactional
    @Delete("delete from course_info where id=#{id}")
    Integer deleteCourse(Integer id);

    @Transactional
    @Delete("DELETE FROM `choose_course` WHERE `course_id` = #{course_id}")
    Integer deleteChooseCourse(Integer course_id);












}



