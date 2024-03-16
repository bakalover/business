package com.example.blps2.repo.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserBody {
    @NonNull
    private String username;

    @NonNull
    private String passwdString;
}
