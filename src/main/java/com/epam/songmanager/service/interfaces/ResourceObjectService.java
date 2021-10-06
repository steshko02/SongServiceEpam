package com.epam.songmanager.service.interfaces;

import com.epam.songmanager.model.entity.StorageType;
import com.epam.songmanager.model.resource.ResourceObj;
import io.minio.errors.*;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface ResourceObjectService {

     void store(InputStream inputStream, StorageType storageType, String ex) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException ;
     List<String> loadAll(StorageType storageType) throws IOException ;
    // Path load(StorageType storageType, String filename) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException ;
    // Resource loadAsResource(StorageType storageType, String filename) throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InsufficientDataException, InternalException;
     ResourceObj getResource(String id);
}

