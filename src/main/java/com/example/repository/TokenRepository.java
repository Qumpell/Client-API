package com.example.repository;

import com.example.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query("""
        select t from Token t inner join Client c on t.client.login = c.login
        where c.login = :clientLogin and (t.expired = false or t.revoked = false )
""")
    List<Token> findAllValidTokensByClient(String clientLogin);

    Optional<Token> findByToken(String token);


}
