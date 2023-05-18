package com.example.ezjob.common.validation;

import com.example.ezjob.common.ApplicationConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Map;


@Component
@RequiredArgsConstructor
public class AllowedPathValidator {
    public boolean isPathAllowed(String requestedPath, String userRole) {
        for (Map.Entry<String, String> entry : ApplicationConstants.Web.Security.ALLOWED_PATH.entrySet()) {
            String pathPattern = entry.getKey();
            String requiredRole = entry.getValue();

            // Use AntPathMatcher to match the requested path against the defined path pattern
            AntPathMatcher pathMatcher = new AntPathMatcher();
            boolean pathMatches = pathMatcher.match(pathPattern, requestedPath);

            // Check if the requested path matches the path pattern and the user has the required role
            if (pathMatches) {
                return userRole.equals(requiredRole);
            }
        }
        return true;
    }

}
