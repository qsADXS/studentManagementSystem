package com.example.studentmanagementsystem.component;
import com.example.studentmanagementsystem.common.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理自定义异常
     *
     */
    @ExceptionHandler(value = DefinitionException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> bizExceptionHandler(DefinitionException e) {
        log.error("自定义异常:{}",e.toString());
        ErrorEnum error = e.getErrorEnum();
        Map<String, Object> errorMap = Map.of("code", error.getCode(), "message", error.getMessage());
        return new ResponseEntity<>(errorMap, HttpStatus.valueOf(error.getCode()));
    }

    /**
     * 处理其他异常
     *
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> exceptionHandler(Exception e) {
        log.error("其他异常:{}",e.toString());
        Map<String, Object> errorMap = Map.of("code",500, "message", e.getMessage());
        return new ResponseEntity<>(errorMap, HttpStatus.valueOf(500));
    }
}
