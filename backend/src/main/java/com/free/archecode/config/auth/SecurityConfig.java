package com.free.archecode.config.auth;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

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
}
