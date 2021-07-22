package com.epam.songmanager.service.impl;

import com.epam.songmanager.service.CheckSum;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;

@Component
public class CheckSumImpl implements CheckSum {

    public String calculate(String filename, MessageDigest md) throws IOException {
        try (
                var fis = new FileInputStream(filename);
                var bis = new BufferedInputStream(fis);
                var dis = new DigestInputStream(bis, md)
        ) {
            while (dis.read() != -1) ;
            md = dis.getMessageDigest();
        }
        var result = new StringBuilder();
        for (byte b : md.digest()) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
