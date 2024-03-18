package com.example.blps2.repo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;

import com.example.blps2.repo.entity.Creds;
import com.example.blps2.repo.entity.CredsList;

@Component
public class CredRepository {

    final static File credRepo = new File("./creds.xml");

    public static void add(Creds creds) throws IOException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(CredsList.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        CredsList credsList;
        try {
            credsList = (CredsList) unmarshaller.unmarshal(credRepo);
        } catch (JAXBException e) {
            credsList = new CredsList();
            credsList.setUserList(new ArrayList<Creds>());
        }

        credsList.append(creds);

        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(credsList, credRepo);
    }

    public static Creds findUserByUsername(String username) throws Exception {
        JAXBContext context = JAXBContext.newInstance(CredsList.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        CredsList credsList = (CredsList) unmarshaller.unmarshal(credRepo);

        for (Creds creds : credsList.getUserList()) {
            if (creds.getUsername().equals(username)) {
                return creds;
            }
        }
        return null;
    }
}
