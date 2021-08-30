package com.epam.songmanager.facades;

import com.epam.songmanager.model.entity.StorageType;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

public interface CreateResource<T> {
     void createFiles(InputStream inputStream,String filename)  throws NoSuchAlgorithmException, IOException;
     boolean supports(StorageType storageType);
}
