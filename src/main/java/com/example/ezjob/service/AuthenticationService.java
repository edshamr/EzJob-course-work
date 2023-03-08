package com.example.ezjob.service;

import com.example.ezjob.persistense.entity.AuthenticationUser;
import lombok.NonNull;

public interface AuthenticationService {
    AuthenticationUser getUserByUsername(@NonNull final String username);
    AuthenticationUser getUserByEmail(@NonNull final String email);
}
