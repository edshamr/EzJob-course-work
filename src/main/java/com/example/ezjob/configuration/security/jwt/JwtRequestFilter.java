package com.example.ezjob.configuration.security.jwt;

import io.jsonwebtoken.JwtException;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(@Nonnull final HttpServletRequest request,
                                    @Nonnull final HttpServletResponse response,
                                    @Nonnull final FilterChain filterChain) throws ServletException, IOException {
        try {
            final var token = jwtTokenUtil.parseJwt(request);

            if (token != null && jwtTokenUtil.isTokenValid(token)) {
                final var authentication = jwtTokenUtil.getAuthentication(token);

                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(request, response);
        } catch (RuntimeException e) {
            handleException(request, response, e);
        }
    }

    private void handleException(HttpServletRequest request, HttpServletResponse response, RuntimeException e) throws IOException {
        if (e instanceof JwtException) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("The provided JWT token is expired or invalid and cannot be used");
        } else {
            throw e;
        }
    }
}
