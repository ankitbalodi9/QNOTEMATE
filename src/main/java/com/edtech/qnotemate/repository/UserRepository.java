package com.edtech.qnotemate.repository;

import com.edtech.qnotemate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    Optional<User> findByUserUuid(UUID userUuid);
    boolean existsByUserName(String username);

    Optional<User> findByUserName(String username);
}
