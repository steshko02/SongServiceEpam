package com.epam.songmanager.service.interfaces;


import com.epam.songmanager.model.file_entity.ResourceDecorator;
import io.minio.errors.*;
import org.springframework.core.io.Resource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Stream;

public interface StorageService <T extends ResourceDecorator> {



    String store(InputStream entity) throws IOException, NoSuchAlgorithmException, ServerException, InsufficientDataException, InternalException, InvalidResponseException, InvalidKeyException, XmlParserException, ErrorResponseException;

    List<String> loadAll();

    Path load(String filename) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException;

    Resource loadAsResource(String filename) throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InsufficientDataException, InternalException;

    void deleteAll() throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException;

    T create(String cs,String filename,long size);

    Resource getResource(String filename) throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, InsufficientDataException, ErrorResponseException;
//    boolean createTmpFile (InputStream inputStream);

}