package com.example.blps2.repo.entity;

import java.util.List;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.NoArgsConstructor;
import jakarta.xml.bind.annotation.XmlElement;

@NoArgsConstructor
@XmlRootElement
public class CredsList {

    private List<Creds> userList;

    @XmlElement(name = "creds")
    public List<Creds> getUserList() {
        return userList;
    }

    public void setUserList(List<Creds> userList) {
        this.userList = userList;
    }

    public void append(Creds creds) {
        this.userList.add(creds);
    }
}