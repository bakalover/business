package com.example.blps2.service;

import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.example.blps2.repo.AlbumRepository;
import com.example.blps2.repo.ImageRepository;
import com.example.blps2.repo.UserRepository;
import com.example.blps2.repo.entity.AlbumDao;
import com.example.blps2.repo.entity.ImageDao;
import com.example.blps2.repo.request.AlbumBody;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service("album_service")
public class AlbumService {

    private final TransactionTemplate transactionTemplate;

    @SuppressWarnings("null")
    @Autowired
    public AlbumService(PlatformTransactionManager transactionManager) {
        this.transactionTemplate = new TransactionTemplate(transactionManager);
        this.transactionTemplate.setTimeout(1);
        this.transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_SERIALIZABLE);
    }

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    public void addNewAlbum(AlbumBody album) throws IllegalArgumentException {
        if (!albumRepository.findByName(album.getName()).isEmpty()) {
            throw new IllegalArgumentException();
        }
        var albumDao = new AlbumDao();
        albumDao.setDescription(album.getDescription());
        albumDao.setName(album.getName());
        albumDao.setRestrictMode(album.getRestrictMode());
        albumDao.setUser(userRepository.findByUsername(album.getUsername()).get());
        albumRepository.save(albumDao);
    }

    public void deleteAlbumById(@NonNull Long id) throws NoSuchElementException {
        albumRepository.findById(id).orElseThrow();
        albumRepository.deleteById(id);
    }

    public void moveImages(@NonNull Long fromId, @NonNull Long toId, List<Long> ids) throws NoSuchElementException {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(@SuppressWarnings("null") TransactionStatus status) {
                try {
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
                    imageRepository.saveAll(toMoveFiltered);
                } catch (NoSuchElementException e) {
                    status.setRollbackOnly();
                    throw new TransactionException("Cannot move elements!\n");
                }
            }

        });

    }

    public AlbumDao getAlbum(@NonNull Long id) throws NoSuchElementException {
        return albumRepository.findById(id).orElseThrow();
    }
}
