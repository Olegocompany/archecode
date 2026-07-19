package com.free.archecode.shared.config.security;

import com.free.archecode.shared.security.token.jwt.JwtFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SuppressWarnings("RedundantThrows")
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JwtFilter jwtFilter;
    private final ContentTypeFilter contentTypeFilter;

    /*
    Перенастройка Spring Security на "api" режим: вывод json ответов, отключение его сессий
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Отключаем CSRF
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login", "/auth/register", "/auth/refresh", "/public/**").permitAll()
                        .anyRequest().authenticated()
                ) // разрешить какие публичные (для всех) и для входа с регистрацией

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
                )
                /*
                По идее логика такова:
                сначала запрос идет в JwtFilter, там идет проверка JWT токена и в случае аутентифицируется.
                При аутентификации создается объект Authentication (то есть аутентифицирован)
                 */
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // фильтр перед проверкой
                .addFilterBefore(contentTypeFilter, JwtFilter.class); // проверка что запрос только про API

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
