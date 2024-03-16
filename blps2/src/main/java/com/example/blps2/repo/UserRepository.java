package com.example.blps2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blps2.repo.entity.UserDao;

public interface UserRepository extends JpaRepository<UserDao, String> {
}
