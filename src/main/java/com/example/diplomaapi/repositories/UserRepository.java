package com.example.diplomaapi.repositories;

import com.example.diplomaapi.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("FROM users WHERE email = :email")
    UserEntity findUserByEmail(@Param("email") String email);

    @Query("FROM users WHERE username = :username")
    UserEntity findUserByName(@Param("username") String username);
}
