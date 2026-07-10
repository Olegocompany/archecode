package com.free.archecode.config.security;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    /*
    Перенастройка Spring Security на "api" режим: вывод json ответов, отключение его сессий
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Отключаем CSRF
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // разрешить все, чтобы доходили до контроллеров

        // Переопределение формата выдаваемых ошибок защиты
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint((req, resp, e) -> {
                            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //401
                            resp.setContentType("application/json");
                            resp.getWriter().write("{\"error\": \"Unauthorized\"}");
                        })
                        .accessDeniedHandler((req, resp, e) -> {
                            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            resp.setContentType("application/json");
                            resp.getWriter().write("{\"error\": \"Access Denied\"}");
                        })
                )
                    // No sessions
                .sessionManagement(sessionManagement ->  sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
