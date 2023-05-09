package com.example.ezjob.persistense.repository.redis;

import com.example.ezjob.persistense.entity.AuthenticationUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationUserRedisRepository extends CrudRepository<AuthenticationUser, Long> {
}
