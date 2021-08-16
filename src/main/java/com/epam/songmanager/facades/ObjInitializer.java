package com.epam.songmanager.facades;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

public interface ObjInitializer<T> {
     void createFiles(InputStream inputStream,String filename)  throws NoSuchAlgorithmException, IOException;
}
