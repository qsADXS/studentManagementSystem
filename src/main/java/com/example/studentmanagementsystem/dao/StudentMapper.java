package com.example.studentmanagementsystem.dao;



import com.example.studentmanagementsystem.pojo.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    @Select("select * from `studentmanagementsystem`.`student_info` where id=#{id}")
    Student selectStudentInfo(String id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Transactional
    @Insert("INSERT INTO `student_info` (`name`, `sex`, `age`, `phone`) VALUES (#{name},#{sex},#{age},#{phone})")
    void insertStudent(Student student);
    @Insert("INSERT INTO choose_course (course_id,student_id) values ( #{id},#{studentId} ) ")
    Integer addCourse(Long studentId, Long id);
    @Delete("DELETE FROM choose_course WHERE student_id = #{studentId} AND course_id = #{id}")
    Integer delCourse(@Param("studentId") Long studentId, @Param("id") Long courseId);
    @Select("select grade from choose_course WHERE student_id = #{studentId} AND course_id = #{id}")
    Integer getCourseGrade(@Param("studentId") Long studentId, @Param("id") Long courseId);
    @Select("SELECT cc.grade, si.`name` FROM choose_course cc LEFT JOIN student_info si ON cc.student_id = si.id WHERE cc.course_id = #{id};")
    List<Map<String, Object>> getAllCourseGrade(@Param("id") Long id);
    @Select("SELECT cc.grade, si.`name` FROM choose_course cc " +
            "LEFT JOIN student_info si ON cc.student_id = si.id " +
            "WHERE cc.course_id IN " +
            "<foreach item='course_id' index='index' collection='courseIds' open='(' separator=',' close=')'>" +
            "#{courseId}" +
            "</foreach> " +
            "AND cc.student_id = #{id}")
    List<Map<String, Object>> getTeacherCourseGrade(@Param("courseIds") List<Long> courseIds, @Param("id") Long id);
    @Select("select * from `studentmanagementsystem`.`student_info`")
    List<Student> getAllStudentInfo();
}

