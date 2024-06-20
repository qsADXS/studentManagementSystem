-- 创建数据库
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = N'studentmanagementsystem')
BEGIN
    CREATE DATABASE studentmanagementsystem;
END;
GO

USE studentmanagementsystem;
GO

-- 创建表结构

-- admin_info 表
CREATE TABLE admin_info (
                            id NVARCHAR(255) NOT NULL COMMENT '管理员ID',
                            name NVARCHAR(255) NULL COMMENT '用户名',
                            password NVARCHAR(255) NOT NULL DEFAULT '123456' COMMENT '密码',
                            sex BIT NULL COMMENT '性别1男0女',
                            age INT NULL COMMENT '年龄',
                            phone NVARCHAR(255) NOT NULL COMMENT '手机号',
    [level] INT NOT NULL DEFAULT 1 COMMENT '权限等级，默认1',
                            PRIMARY KEY (id)
);
GO

-- 插入数据到 admin_info 表
INSERT INTO admin_info (id, name, password, sex, age, phone, [level])
VALUES ('1', '张三', '123456', 1, 18, '12345678910', 1);
GO

-- choose_course 表
CREATE TABLE choose_course (
                               course_id BIGINT NOT NULL,
                               student_id BIGINT NOT NULL,
                               grade NVARCHAR(255) NULL COMMENT '分数',
                               PRIMARY KEY (course_id, student_id)
);
GO

-- college_info 表
CREATE TABLE college_info (
                              id BIGINT IDENTITY(1,1) NOT NULL COMMENT 'id',
                              name NVARCHAR(255) NULL COMMENT '学院名称',
                              PRIMARY KEY (id)
);
GO

-- course_info 表
CREATE TABLE course_info (
                             id BIGINT IDENTITY(1,1) NOT NULL COMMENT 'id',
                             name NVARCHAR(255) NULL COMMENT '课程名称',
                             credit INT NULL COMMENT '学分',
                             teacher_id INT NOT NULL COMMENT '负责教师id',
                             PRIMARY KEY (id)
);
GO

-- major_info 表
CREATE TABLE major_info (
                            id BIGINT NOT NULL COMMENT 'id',
                            name NVARCHAR(255) NULL COMMENT '专业名称',
                            college_id BIGINT NULL COMMENT '学院id',
                            PRIMARY KEY (id)
);
GO

-- student_info 表
CREATE TABLE student_info (
                              id NVARCHAR(255) NOT NULL COMMENT 'ID学号',
                              name NVARCHAR(255) NULL COMMENT '用户名',
                              password NVARCHAR(255) NOT NULL DEFAULT '123456' COMMENT '密码',
                              sex BIT NULL COMMENT '性别1男0女',
                              age INT NULL COMMENT '年龄',
    [level] INT NULL DEFAULT 3 COMMENT '权限等级，默认3',
                              major_id INT NULL COMMENT '学生专业id',
                              PRIMARY KEY (id)
);
GO

-- 插入数据到 student_info 表
INSERT INTO student_info (id, name, password, sex, age, [level], major_id)
VALUES
('100001', '小明', '123456', 1, 19, 3, 1),
('111112', '李四', '123456', 1, 18, 3, 1);
GO

-- teacher_info 表
CREATE TABLE teacher_info (
                              id NVARCHAR(255) NOT NULL COMMENT 'ID职工号',
                              name NVARCHAR(255) NULL COMMENT '用户名',
                              password NVARCHAR(255) NULL COMMENT '密码',
                              sex BIT NULL COMMENT '性别1男0女',
                              age INT NULL COMMENT '年龄',
    [level] INT NULL DEFAULT 2 COMMENT '权限等级，默认2',
                              title NVARCHAR(255) NULL COMMENT '职称',
                              college_id INT NULL COMMENT '学院id',
                              PRIMARY KEY (id)
);
GO
