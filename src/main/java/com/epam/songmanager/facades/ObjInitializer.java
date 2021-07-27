package com.epam.songmanager.facades;

import org.farng.mp3.TagException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface ObjInitializer<T> {
     void init(T entity) throws NoSuchAlgorithmException, TagException, IOException;
}
