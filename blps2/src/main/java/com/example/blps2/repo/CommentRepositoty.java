package com.example.blps2.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blps2.repo.entity.CommentDao;
import com.example.blps2.repo.entity.ImageDao;

@Repository
public interface CommentRepositoty extends JpaRepository<CommentDao, Long> {
    List<CommentDao> findByImage(ImageDao image);
}
