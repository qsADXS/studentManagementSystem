package com.example.studentmanagementsystem.pojo;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//专业
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Major {
    Integer id;
    String name;
    Integer college_id;
}
