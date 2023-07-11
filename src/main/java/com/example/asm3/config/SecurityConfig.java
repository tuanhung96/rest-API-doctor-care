package com.example.asm3.config;

import com.example.asm3.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private UserService userService;
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public SecurityConfig(UserService userService, JwtRequestFilter jwtRequestFilter) {
        this.userService = userService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    //authenticationProvider bean definition
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService); //set the custom user details service
        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
        return auth;
    }

    //passwordEncoder bean definition
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //authenticationManager bean definition
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/authenticate").permitAll()
                                .requestMatchers("/registerUser").permitAll()
                                .requestMatchers("/verify").permitAll()
                                .requestMatchers("/forgetPassword").permitAll()
                                .requestMatchers("/resetPassword").permitAll()
                                .requestMatchers("/patients/**").hasRole("DOCTOR")
                                .requestMatchers("/schedules/accept/**").hasRole("DOCTOR")
                                .requestMatchers("/schedules/cancel/**").hasRole("DOCTOR")
                                .requestMatchers("/doctors/email/**").hasRole("DOCTOR")
                                .requestMatchers("/users/disable/**").hasRole("ADMIN")
                                .requestMatchers("/users/enable/**").hasRole("ADMIN")
                                .requestMatchers("/schedules/patients/**").hasRole("ADMIN")
                                .requestMatchers("/schedules/doctors/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .sessionManagement(session ->
                        session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                .maximumSessions(1)
                                .maxSessionsPreventsLogin(false)     // second login will cause the first to be invalidated
                );
//                .exceptionHandling(exception ->
//                        exception
//                                .authenticationEntryPoint((request, response, e) -> {
//                                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED,e.getMessage());
//                                }
//                        )
//                );

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

}
