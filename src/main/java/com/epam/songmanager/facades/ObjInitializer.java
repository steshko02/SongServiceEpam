package com.epam.songmanager.facades;

import org.farng.mp3.TagException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

public interface ObjInitializer<T> {
     void createFiles(InputStream inputStream)  throws NoSuchAlgorithmException, IOException;
}
