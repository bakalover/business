package com.example.blps2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blps2.repo.entity.AlbumDao;
import com.example.blps2.repo.entity.Complaint;
import com.example.blps2.repo.entity.ImageDao;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByImage(ImageDao image);
}
