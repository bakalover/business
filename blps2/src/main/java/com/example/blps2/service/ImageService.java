package com.example.blps2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.blps2.repo.AlbumRepository;
import com.example.blps2.repo.CommentRepositoty;
import com.example.blps2.repo.ImageRepository;
import com.example.blps2.repo.UserRepository;
import com.example.blps2.repo.entity.AlbumDao;
import com.example.blps2.repo.entity.CommentDao;
import com.example.blps2.repo.entity.ImageDao;
import com.example.blps2.repo.request.CommentBody;
import jakarta.transaction.Transactional;
import lombok.NonNull;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service("image_service")
@Transactional
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private CommentRepositoty commentRepositoty;

    @Autowired
    private UserRepository userRepositoty;

    // ~todo transaction
    public void addNewImage(MultipartFile file, @NonNull Long albumId, Boolean face)
            throws IOException, NoSuchElementException {
        var imageDao = new ImageDao();
        var album = albumRepository.findById(albumId).orElseThrow();
        imageDao.setAlbum(album);
        imageDao.setName(file.getOriginalFilename());
        imageDao.setData(file.getBytes());
        imageDao.setFace(face);
        var images = imageRepository.findByAlbum(album);
        images.forEach(image -> {
            if (image.getFace()) {
                image.setFace(false);
                imageRepository.save(image);
            }
        });
        imageRepository.save(imageDao);
    }

    public ImageDao findById(@NonNull Long id) throws NoSuchElementException {
        return imageRepository.findById(id).orElseThrow();
    }

    public List<ImageDao> findByAlbum(AlbumDao albumDao) {
        return imageRepository.findByAlbum(albumDao);
    }

    public void deleteById(@NonNull Long id) throws NoSuchElementException {
        imageRepository.findById(id).orElseThrow();
        imageRepository.deleteById(id);
    }

    public void addComment(CommentBody comment) throws NoSuchElementException {
        var dao = new CommentDao();
        dao.setImage(imageRepository.findById(comment.getPicId()).orElseThrow());
        dao.setText(comment.getText());
        dao.setUser(userRepositoty.findById(comment.getUsername()).orElseThrow());
        commentRepositoty.save(dao);
    }

    public List<CommentDao> getComments(@NonNull Long picId) {
        return commentRepositoty.findByImage(imageRepository.findById(picId).orElseThrow());
    }
}
