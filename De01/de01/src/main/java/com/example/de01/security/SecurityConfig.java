package com.example.de01.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User
                .withUsername("admin")
                .password(bCryptPasswordEncoder().encode("pass"))
                .roles("ADMIN")
                .build();
        List<UserDetails> listUser = new ArrayList<>();
        listUser.add(user);
        return new InMemoryUserDetailsManager(listUser);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(c -> c.disable())
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/nhan-vien/getAll").permitAll()
                                .requestMatchers("/nhan-vien/display").permitAll()
                                .requestMatchers("/nhan-vien/getAllDisplay").permitAll()
                                .requestMatchers("/nhan-vien/update/**").permitAll()
                                .requestMatchers("/nhan-vien/delete/**").permitAll()
                                .requestMatchers("/nhan-vien/detail/**").permitAll()
                                .requestMatchers("/nhan-vien/add/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()) //giup minh crud dc tren postman ma van chay secrity.
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults());
        return http.build();
    }
}
