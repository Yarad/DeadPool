package com.services;

import com.services.interfaces.IKeyProvider;
import org.springframework.stereotype.Service;

@Service
public class KeyProvider implements IKeyProvider {
    final private String HS256Key = "OUYTCJY#$%^&*()csbdgtVH52``|BJYHkr12&&////53kjdfkhP*OGUB*--wfeliiewdf";

    @Override
    public String getHS256Key() {
        return HS256Key;
    }
}
