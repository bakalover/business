package com.example.blps2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blps2.repo.entity.AlbumDao;
import java.util.List;


@Repository
public interface AlbumRepository extends JpaRepository<AlbumDao ,Long> {
    List<AlbumDao> findByName(String name);
}
