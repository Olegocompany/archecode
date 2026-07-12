package com.free.archecode.shared.config.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
@Order(1)
public class ContentTypeFilter implements Filter {

    private static final Set<String> ALLOWED_METHODS = Set.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS");
    private static final Set<String> METHODS_REQUIRING_JSON = Set.of("POST", "PUT", "PATCH");
    private static final String JSON_CONTENT_TYPE = "application/json";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        String method = httpRequest.getMethod();
        String contentType = httpRequest.getContentType();

        if (!ALLOWED_METHODS.contains(method)) {
            httpResponse.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            httpResponse.setContentType(JSON_CONTENT_TYPE);
            httpResponse.getWriter().write("{\"error\": \"Method '" + method + "' not allowed\"}");
            return;
        }

        if (METHODS_REQUIRING_JSON.contains(method)) {
            if (contentType == null || !contentType.startsWith(JSON_CONTENT_TYPE)) {
                httpResponse.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
                httpResponse.setContentType(JSON_CONTENT_TYPE);
                httpResponse.getWriter().write("{\"error\": \"Content-Type must be application/json\"}");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}