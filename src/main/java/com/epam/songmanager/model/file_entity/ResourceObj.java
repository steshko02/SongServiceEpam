package com.epam.songmanager.model.file_entity;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

public interface ResourceObj {
//    void save(InputStream is) throws IOException, NoSuchAlgorithmException;
    InputStream read() throws IOException;
//    ResourceObj save(ResourceObj resourceObj) throws IOException;
}
