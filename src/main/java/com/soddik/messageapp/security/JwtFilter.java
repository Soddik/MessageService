package com.soddik.messageapp.security;

import com.soddik.messageapp.exception.JwtAuthenticationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends GenericFilterBean {
    @Value("${jwt.header}")
    private String authHeader;
    private final JwtProvider jwtProvider;

    public JwtFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            if (((HttpServletRequest) servletRequest).getHeader(authHeader) != null) {
                String[] header = ((HttpServletRequest) servletRequest).getHeader(authHeader).split("_");
                if (header.length < 2 || !header[0].equals("Bearer")) {
                    throw new JwtAuthenticationException("Wrong authorization header");
                }

                String token = header[1];
                if (token != null && jwtProvider.validateToken(token)) {
                    Authentication authentication = jwtProvider.getAuthentication(token);
                    if (authentication != null) {
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        } catch (JwtAuthenticationException e) {
            SecurityContextHolder.clearContext();
            if (e.getStatus() != null) {
                ((HttpServletResponse) servletResponse).sendError(e.getStatus().value());
            }
            throw new JwtAuthenticationException(e.getMessage());
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
