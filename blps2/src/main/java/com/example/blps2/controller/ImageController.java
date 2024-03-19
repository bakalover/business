package com.example.blps2.controller;

import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.blps2.repo.entity.CommentDao;
import com.example.blps2.repo.entity.Complaint;
import com.example.blps2.repo.entity.ImageDao;
import com.example.blps2.repo.request.CommentBody;
import com.example.blps2.repo.request.ComplaintBody;
import com.example.blps2.service.ImageService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/image")
public class ImageController {
    private final String okMsg = "Ok\n";

    @Autowired
    private ImageService imageService;

    @PostMapping("/add")
    public ResponseEntity<String> addImage(@RequestParam("path") MultipartFile file,
            @RequestParam("albumId") Long albumId,
            @RequestParam("face") Boolean face) {
        try {
            imageService.addNewImage(file, albumId, face);
            return ResponseEntity.ok().body(okMsg);
        } catch (TransactionException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/comment")
    public ResponseEntity<String> postMethodName(@RequestBody CommentBody comment) {
        try {
            imageService.addComment(comment);
            return ResponseEntity.ok().body(okMsg);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid User or picture!\n");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable Long id) {
        try {
            imageService.deleteById(id);
            return ResponseEntity.ok().body(okMsg);

        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body("Cannot find specified image!\n");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageDao> getImage(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(imageService.findById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentDao>> getImageComments(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(imageService.getComments(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/complaint")
    public ResponseEntity<String> makeComplaint(@RequestBody ComplaintBody complaint) {
        try {
            imageService.makeComplaint(complaint);
            return ResponseEntity.ok().body(okMsg);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Cannot make complaint, wrong info!");
        }

    }

    @GetMapping("/complaints/{id}")
    public ResponseEntity<List<Complaint>> register(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(imageService.getComplaint(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

}
