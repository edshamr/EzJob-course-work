package com.example.ezjob.persistense.repository;

import com.example.ezjob.persistense.entity.AuthenticationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationUserRepository extends JpaRepository<AuthenticationUser, Long> {
  AuthenticationUser findByUsername(String username);
  AuthenticationUser findByEmail(String email);
  Optional<AuthenticationUser> findById(Long id);
}
