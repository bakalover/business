package com.example.blps2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blps2.repo.AlbumRepository;
import com.example.blps2.repo.ImageRepository;
import com.example.blps2.repo.entity.AlbumDao;
import com.example.blps2.repo.entity.ImageDao;
import com.example.blps2.repo.request.AlbumBody;
import jakarta.transaction.Transactional;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service("album_service")
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Transactional
    public void addNewAlbum(AlbumBody album) {
        var albumDao = new AlbumDao();
        albumDao.setDescription(album.getDescription());
        albumDao.setName(album.getName());
        albumDao.setRestrictMode(album.getRestrictMode());
        albumRepository.save(albumDao);
    }

    public void deleteAlbumById(@NonNull Long id) throws NoSuchElementException {
        albumRepository.findById(id).orElseThrow();
        albumRepository.deleteById(id);
    }

    // ~todo transaction
    public void moveImages(@NonNull Long fromId, @NonNull Long toId, List<Long> ids) throws NoSuchElementException {
        // todo secur check
        AlbumDao from = albumRepository.findById(fromId).orElseThrow();
        AlbumDao to = albumRepository.findById(toId).orElseThrow();
        List<ImageDao> toMove = imageRepository.findByAlbum(from);
        List<ImageDao> alreadyHave = imageRepository.findByAlbum(from);
        alreadyHave.forEach(image -> {
            if (image.getFace()) {
                image.setFace(false);
                imageRepository.save(image);
            }
        });
        toMove.forEach(image -> image.setAlbum(to));
        List<ImageDao> toMoveFiltered = new ArrayList<>();
        for (ImageDao imageDao : toMove) {
            if (ids.contains(imageDao.getId())) {
                toMoveFiltered.add(imageDao);
            }
        }
        // transaction emulation -> add by one
        imageRepository.saveAll(toMoveFiltered);

    }

    public AlbumDao getAlbum(@NonNull Long id) throws NoSuchElementException {
        return albumRepository.findById(id).orElseThrow();
    }
}
