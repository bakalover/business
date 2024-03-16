package com.example.blps2.repo.request;

import lombok.NonNull;
import lombok.Data;

@Data
public class ImageBody {

    @NonNull
    private String name;

    @NonNull
    private Long albumId;
}
