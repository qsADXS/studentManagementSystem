package com.example.studentmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//传递给前端的消息
public class LoginDTO {
    String token;
    String name;
}
