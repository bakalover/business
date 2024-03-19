package com.example.blps2.service;

import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.example.blps2.repo.AlbumRepository;
import com.example.blps2.repo.CommentRepositoty;
import com.example.blps2.repo.ComplaintRepository;
import com.example.blps2.repo.ImageRepository;
import com.example.blps2.repo.UserRepository;
import com.example.blps2.repo.entity.AlbumDao;
import com.example.blps2.repo.entity.CommentDao;
import com.example.blps2.repo.entity.Complaint;
import com.example.blps2.repo.entity.ImageDao;
import com.example.blps2.repo.request.CommentBody;
import com.example.blps2.repo.request.ComplaintBody;

import lombok.NonNull;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service("image_service")
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private CommentRepositoty commentRepositoty;

    @Autowired
    private UserRepository userRepositoty;

    @Autowired
    private ComplaintRepository complaintRepository;

    private final TransactionTemplate transactionTemplate;

    @SuppressWarnings("null")
    @Autowired
    public ImageService(PlatformTransactionManager transactionManager) {
        this.transactionTemplate = new TransactionTemplate(transactionManager);
        this.transactionTemplate.setTimeout(1);
        this.transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_SERIALIZABLE);
    }

    public void addNewImage(MultipartFile file, @NonNull Long albumId, Boolean face)
            throws TransactionException {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {

            @Override
            protected void doInTransactionWithoutResult(@SuppressWarnings("null") TransactionStatus status) {
                try {
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
                } catch (IOException e) {
                    status.setRollbackOnly();
                    throw new TransactionException("Cannot read image!\n");
                } catch (NoSuchElementException e) {
                    status.setRollbackOnly();
                    throw new TransactionException("Album doesn't exist!\n");
                }
            }

        });
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

    public List<CommentDao> getComments(@NonNull Long picId) throws NoSuchElementException {
        return commentRepositoty.findByImage(imageRepository.findById(picId).orElseThrow());
    }

    public void makeComplaint(ComplaintBody complaintBody) throws NoSuchElementException {
        var complaint = new Complaint();
        complaint.setDescription(complaintBody.getDescription());
        complaint.setImage(imageRepository.findById(complaintBody.getPicId()).get());
        complaint.setUser(userRepositoty.findByUsername(complaintBody.getUsername()).get());
        complaintRepository.save(complaint);
    }

    public List<Complaint> getComplaint(@NonNull Long picId) throws NoSuchElementException {
        return complaintRepository.findByImage(imageRepository.findById(picId).get());
    }
}
