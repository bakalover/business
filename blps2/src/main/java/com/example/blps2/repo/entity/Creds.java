package com.example.blps2.repo.entity;

import jakarta.xml.bind.annotation.XmlRootElement;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement
public class Creds {
    private String username;
    private String hashedPasswd;
}
