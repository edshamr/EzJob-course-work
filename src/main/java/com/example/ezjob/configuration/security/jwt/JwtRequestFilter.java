package com.example.ezjob.configuration.security.jwt;

import com.example.ezjob.common.validation.AllowedPathValidator;
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
    private final AllowedPathValidator pathValidator;

    @Override
    protected void doFilterInternal(@Nonnull final HttpServletRequest request,
                                    @Nonnull final HttpServletResponse response,
                                    @Nonnull final FilterChain filterChain) throws ServletException, IOException {
        final var token = jwtTokenUtil.parseJwt(request);

        if (token != null && jwtTokenUtil.isTokenValid(token)) {
            final var authentication = jwtTokenUtil.getAuthentication(token);
            final var userRole = jwtTokenUtil.getRoles(token);

            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            final var isAllowed = pathValidator.isPathAllowed(request.getRequestURI(), userRole);
            if (!isAllowed) {
                throw new RuntimeException("Something");
            }
        }
        filterChain.doFilter(request, response);
    }


    private void handleException(HttpServletResponse response, RuntimeException e) throws IOException {
        if (e instanceof JwtException) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("The provided JWT token is expired or invalid and cannot be used");
        } else {
            throw e;
        }
    }
}
