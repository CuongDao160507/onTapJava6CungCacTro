package com.example.demau.security;

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
//    password: 123 ---> password: 3289189213980320sdaksad8932ksdak1 (ko hieu nghia)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        List<UserDetails> userDetailsList = new ArrayList<>();
        UserDetails user = User
                .withUsername("TH03230")
                .password(passwordEncoder().encode("SD20204"))
                .build();
        userDetailsList.add(user);
        return new InMemoryUserDetailsManager(userDetailsList);
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .cors(Customizer.withDefaults())
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(
//                        auth -> auth
//                                .requestMatchers("/don-hang/display").permitAll()
//                                .anyRequest().authenticated()
//                )
//                .formLogin(Customizer.withDefaults())
//                .logout(Customizer.withDefaults());
//        return httpSecurity.build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(c -> c.disable()) // csrf - bao mat cho add, update, delete -> tat -> chay bth
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/don-hang/display").permitAll()
//                                .requestMatchers("/cuongDao/register/**").permitAll()
//                                .requestMatchers("/don-hang/delete").hasRole("ADMIN")
                                .anyRequest().authenticated()  // duoi cung - ko can qtam - auto phai login moi dc vao.

                )
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults());
        return http.build();
    }
}
