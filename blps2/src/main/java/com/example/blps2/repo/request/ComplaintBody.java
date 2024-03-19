package com.example.blps2.repo.request;

import lombok.*;

@Data
public class ComplaintBody {
    @NonNull
    private String username;

    @NonNull
    private String description;

    @NonNull
    private Long picId;
}