package com.damian.wnukowski.projectboard.repository;

import com.damian.wnukowski.projectboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
