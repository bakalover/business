package com.example.blps2.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blps2.repo.entity.CommentDao;
import com.example.blps2.repo.entity.ImageDao;

public interface CommentRepositoty extends JpaRepository<CommentDao, Long> {
    List<CommentDao> findByImage(ImageDao image);
}
