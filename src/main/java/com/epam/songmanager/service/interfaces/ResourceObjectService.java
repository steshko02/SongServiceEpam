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
     ResourceObj getResource(String id);
}

