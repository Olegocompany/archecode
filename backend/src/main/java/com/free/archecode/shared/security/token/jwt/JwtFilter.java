package com.free.archecode.shared.security.token.jwt;

import com.free.archecode.shared.config.security.user.imps.UserAuthDetailsImp;
import com.free.archecode.shared.config.security.user.imps.UserAuthDetailsServiceImp;
import com.free.archecode.shared.security.token.jwt.serviceImp.JwtServiceImp;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private JwtServiceImp jwtService;
    private UserAuthDetailsServiceImp userDetailsService;

    @Override
    @NullMarked
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        String authToken = request.getHeader("Authorization");

        if (authToken == null || !authToken.startsWith("Bearer ")) {
            try {
                filterChain.doFilter(request, response);
            } catch (IOException|ServletException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        String token = authToken.substring(7);
        try {
            String username = jwtService.extractUsername(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserAuthDetailsImp userDetails = userDetailsService.loadUserByUsername(username);

                if (jwtService.validateToken(token, userDetails)) {
                    /*
                    credentials null потому что уже проверили токен. Spring Security вроде как ожидает уже пустое, потому что в токене пароля нет, а мы его уже проверили

                    1 - userDetails - он же Principal
                    2 - credentials (ну, пароль не нужен)
                    3 - authorities - роли
                     */
                    UsernamePasswordAuthenticationToken authDetails =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authDetails.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authDetails);
                }
            }
        } catch (MalformedJwtException | ExpiredJwtException | SignatureException e ) {/* ничего не делать - токен не валиден */}
        try {
            filterChain.doFilter(request, response);
        } catch (IOException|ServletException e) {
            throw new RuntimeException(e);
        }
    }

}
