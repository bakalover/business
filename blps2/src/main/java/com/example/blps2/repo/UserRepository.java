package com.example.blps2.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blps2.repo.entity.UserDao;

@Repository
public interface UserRepository extends JpaRepository<UserDao, String> {
    Optional<UserDao> findByUsername(String username);
}
