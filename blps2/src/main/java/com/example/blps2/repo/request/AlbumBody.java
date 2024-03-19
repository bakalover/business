package com.example.blps2.repo.request;

import com.example.blps2.repo.entity.UserRestriction;
import lombok.NonNull;
import lombok.Data;

@Data
public class AlbumBody {
    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    private String username;

    @NonNull
    private UserRestriction restrictMode;
}