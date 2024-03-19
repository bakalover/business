package com.example.blps2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blps2.repo.entity.AlbumDao;
import com.example.blps2.repo.entity.ImageDao;
import com.example.blps2.repo.entity.UserDao;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ImageDao ,Long> {
    List<ImageDao> findByAlbum(AlbumDao album);
}