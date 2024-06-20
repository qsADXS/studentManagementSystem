package com.example.studentmanagementsystem.component;


import com.example.studentmanagementsystem.common.ErrorEnum;
import com.example.studentmanagementsystem.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Service
public class JwtTokenFilter extends OncePerRequestFilter {


    private static String[] allowUrls;
    private static String tokenHeader;

    @Value("${allow-url}")
    private void setAllowUrls(String[] allowUrls){
        JwtTokenFilter.allowUrls = allowUrls;
    }
    @Value("${jwt.tokenHeader}")
    private void setTokenHeader(String tokenHeader){
        JwtTokenFilter.tokenHeader = tokenHeader;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        log.info("经过tokenFilter");
        log.info("{}请求：{}",request.getMethod(),request.getRequestURI());
        String token = request.getHeader(tokenHeader);
        log.info("token:{}",token);
        if(token == null && matchUrl(request.getRequestURI(),allowUrls)){
            log.info("无需验证，通过");
            filterChain.doFilter(request, response);
            return;
        }


        //验证是否正确
        if(token == null||!JwtUtils.isVerify(token)){
            //todo token错误
            throw new DefinitionException(ErrorEnum.NO_AUTH);
        }
        log.info("通过");
        filterChain.doFilter(request, response);
    }

    /***
     * 用来判断是否需要token，在yaml文件中配置
     * @param requestURI 要判断的路径，如"/login"
     * @return 返回true则无需验证
     */
    private boolean matchUrl(String requestURI,String[] urls) {
        for(String allowUrl:urls){
            // 首先检查是否是完全匹配的情况
            if (requestURI.equals(allowUrl)) {
                return true;
            }
            // 然后检查是否有通配符的情况
            if (allowUrl.endsWith("/**")) {
                String pattern = allowUrl.substring(0, allowUrl.length() - 3);
                if(requestURI.startsWith(pattern)){
                    log.info("匹配成功");
                    return true;
                }
            }
        }
        // 如果以上条件都不满足，则不匹配
        return false;
    }

}
