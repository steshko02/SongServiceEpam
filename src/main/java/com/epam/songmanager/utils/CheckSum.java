package com.epam.songmanager.utils;

import com.epam.songmanager.model.entity.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

public interface CheckSum {

    String calculate(InputStream stream, MessageDigest md) throws IOException;
    boolean check(String storageFile, String resourceFile) throws IOException;
    String  create(MessageDigest md);
}
