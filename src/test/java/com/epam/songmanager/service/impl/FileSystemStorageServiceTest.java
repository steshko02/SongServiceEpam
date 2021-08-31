package com.epam.songmanager.service.impl;

import com.epam.songmanager.exceptions.StorageException;
import com.epam.songmanager.model.resource.FileStorageEntity;
import com.epam.songmanager.service.interfaces.StorageService;
import io.minio.errors.*;
import org.junit.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;


@SpringBootTest
@PrepareForTest(UUID.class)
class FileSystemStorageServiceTest {

    private File fileObj = null;

    @After
    public  void  delete(){
        fileObj.delete();
    }


    @Autowired
    private StorageService<FileStorageEntity> fileSystemStorageService;


    @Test
     void storeIsNullStream() throws StorageException{
        Throwable thrown = assertThrows(StorageException.class, () -> {
            fileSystemStorageService.store(new ByteArrayInputStream(new byte[0]));
        });
    }


    @Test
    void loadAsResource() throws IOException, ServerException, ErrorResponseException, NoSuchAlgorithmException, InsufficientDataException, InternalException, InvalidResponseException, XmlParserException, InvalidKeyException {

        StorageService<FileStorageEntity> spyObj = spy(fileSystemStorageService);

        String filename = "test.mp3";

         fileObj = new File(filename);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileObj);
            fileOutputStream.write("test123".getBytes(StandardCharsets.UTF_8));

            Mockito.when(spyObj.load(filename)).thenReturn(Path.of(filename));

            Resource resource = spyObj.loadAsResource(filename);

            Assertions.assertTrue(resource.exists());

            byte[] context = resource.getInputStream().readNBytes("test123".length());

            Assertions.assertArrayEquals(context,"test123".getBytes());
            fileObj.delete();
        }
        finally {
            fileObj.delete();
        }
    }

}