package com.example.blps2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blps2.repo.entity.AlbumDao;
import com.example.blps2.repo.entity.ImageDao;
import java.util.List;

public interface ImageRepository extends JpaRepository<ImageDao ,Long> {
    List<ImageDao> findByAlbum(AlbumDao album);
}