package com.example.ezjob.controller;

import com.example.ezjob.configuration.security.jwt.JwtTokenUtil;
import com.example.ezjob.persistense.entity.RoleName;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RolesController {
    private final JwtTokenUtil jwtTokenUtil;

    @GetMapping("/allRoles")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getRoles() {
        return Arrays.stream(RoleName.values())
                .map(RoleName::name)
                .toList();
    }

    @GetMapping("/resolve")
    @ResponseStatus(HttpStatus.OK)
    public String resolveRoles(@Nonnull final HttpServletRequest request) {
        final var token = jwtTokenUtil.parseJwt(request);
        final var role = jwtTokenUtil.getRoles(token);
        return role;
    }
}
