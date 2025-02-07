package com.springboot.lecture.data.repository;

import com.springboot.lecture.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUid(String uid); // Optional 사용

    UserDetails getByUid(String username);
}
