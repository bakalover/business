package com.example.blps2.controller;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.blps2.repo.entity.AlbumDao;
import com.example.blps2.repo.request.AlbumBody;
import com.example.blps2.service.AlbumService;
import com.example.blps2.service.ImageService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/album")
public class AlbumController {

    private final String okMsg = "Ok\n";

    @Autowired
    private AlbumService albumService;


    @PostMapping("/add")
    public ResponseEntity<String> addAlbum(@RequestBody AlbumBody album) {
        try {
            albumService.addNewAlbum(album);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Album already exist!\n");
        }
        return ResponseEntity.ok().body(okMsg);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAlbum(@PathVariable Long id) {
        try {
            albumService.deleteAlbumById(id);
            return ResponseEntity.ok().body(okMsg);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body("Cannot find specified album!\n");
        }
    }

    @PostMapping("/move")
    public ResponseEntity<String> movePics(@RequestParam Long from, @RequestParam Long to,
            @RequestBody ArrayList<Long> ids) {
        try {
            albumService.moveImages(from, to, ids);
            return ResponseEntity.ok().body(okMsg);

        } catch (TransactionException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDao> getAlbum(@PathVariable Long id) {
        try {
            var album = albumService.getAlbum(id);
            return ResponseEntity.ok().body(album);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
