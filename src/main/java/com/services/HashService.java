package com.services;

import com.services.interfaces.IHashService;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class HashService implements IHashService {
    public String getMD5Hash(String value) {
        MessageDigest md = null;
        String hashValue="";
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(value.getBytes());
            hashValue = DatatypeConverter.printHexBinary(md.digest()).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            //что возвращать?
            return "";
        }
        return hashValue;
    }
}
