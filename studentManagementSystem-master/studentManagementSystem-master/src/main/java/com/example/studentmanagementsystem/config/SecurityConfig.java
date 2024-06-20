package com.example.studentmanagementsystem.config;

import com.example.studentmanagementsystem.component.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Autowired
    private JwtTokenFilter tokenFilter;
    @Value("${allow-url}")
    private String[] allowUrl;
    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity)  throws Exception {
        httpSecurity.authorizeHttpRequests((authz) -> authz
                        .requestMatchers(allowUrl).permitAll()
                        .anyRequest().permitAll()
                        //.anyRequest().authenticated()

                ).addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                ;
        return httpSecurity.build();
    }
}
