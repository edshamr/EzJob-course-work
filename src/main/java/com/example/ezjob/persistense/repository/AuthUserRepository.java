package com.example.ezjob.persistense.repository;

import com.example.ezjob.persistense.entity.AuthenticationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepository extends JpaRepository<AuthenticationUser, Long> {

  AuthenticationUser findByUsername(String username);

  AuthenticationUser findByEmail(String email);
}
