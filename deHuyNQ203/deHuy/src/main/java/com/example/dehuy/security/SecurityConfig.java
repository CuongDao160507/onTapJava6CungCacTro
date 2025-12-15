package com.example.dehuy.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        List<UserDetails> users = new ArrayList<>();
        UserDetails user = User
                .withUsername("user")
                .password(passwordEncoder().encode("123"))
                .roles("USER")
                .build();
        users.add(user);
        UserDetails admin = User
                .withUsername("admin")
                .password(passwordEncoder().encode("234"))
                .roles("ADMIN")
                .build();
        users.add(admin);
        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable()) // Tắt CSRF
                .authorizeHttpRequests(auth -> auth
                        // Cho phép file tĩnh và trang login
                        .requestMatchers("/login", "/logout", "/resources/**", "/css/**").permitAll()
                        .requestMatchers("/employee/getAllDisplay").permitAll()

                        // USER và ADMIN đều được xem và thêm
                        .requestMatchers("/employee/display", "/employee/getAll").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/employee/add").hasAnyRole("USER", "ADMIN")

                        // Các quyền còn lại (Sửa, Xóa...) chỉ dành cho ADMIN
                        .requestMatchers("/employee/**").hasRole("ADMIN")

                        // Còn lại bắt buộc đăng nhập
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()) // Quan trọng cho Postman
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults());

        return http.build();
    }
}
