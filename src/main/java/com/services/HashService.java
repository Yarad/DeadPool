package com.services;

import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class HashService {
    public String getMD5Hash(String value) {
        MessageDigest md = null;
        String hashValue="";
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(value.getBytes());
            hashValue = DatatypeConverter.printHexBinary(md.digest()).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            //TODO: что возвращать?
            return "";
        }
        return hashValue;
    }
}
