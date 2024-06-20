package com.example.studentmanagementsystem.dao;


import com.example.studentmanagementsystem.pojo.Student;
import org.apache.ibatis.annotations.*;

@Mapper
public interface StudentMapper {
    @Select("select * from `studentmanagementsystem`.`student_info` where id=#{id}")
    Student selectStudentInfo(String id);
    @Insert("INSERT INTO choose_course (course_id,student_id) values ( #{id},#{studentId} ) ")
    Integer addCourse(Long studentId, Long id);
    @Delete("DELETE FROM choose_course WHERE student_id = #{studentId} AND course_id = #{id}")
    Integer delCourse(@Param("studentId") Long studentId, @Param("id") Long courseId);
    @Select("select grade from choose_course WHERE student_id = #{studentId} AND course_id = #{id}")
    Integer getCourseGrade(@Param("studentId") Long studentId, @Param("id") Long courseId);
}
