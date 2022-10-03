package com.example.diplomaapi.repositories;


import com.example.diplomaapi.entities.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
    @Query("FROM tokens WHERE owner = :owner AND type = 1")
    TokenEntity findConfirmTokenByOwner(@Param("owner") int owner);
}
