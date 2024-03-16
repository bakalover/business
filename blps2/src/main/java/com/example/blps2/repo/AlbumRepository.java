package com.example.blps2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.blps2.repo.entity.AlbumDao;

public interface AlbumRepository extends JpaRepository<AlbumDao ,Long> {
}
