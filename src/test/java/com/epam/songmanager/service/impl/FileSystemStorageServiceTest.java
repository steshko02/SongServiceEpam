package com.epam.songmanager.service.impl;

import com.epam.songmanager.config.StorageProperties;
import com.epam.songmanager.exceptions.StorageException;
import com.epam.songmanager.exceptions.StorageFileNotFoundException;
import com.epam.songmanager.model.file_entity.FileStorageEntity;
import com.epam.songmanager.service.ResourceService;
import com.epam.songmanager.service.StorageService;
import com.epam.songmanager.utils.CheckSum;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@PrepareForTest(UUID.class)
class FileSystemStorageServiceTest {

    File fileObj = null;

    @After
    public  void  delete(){
        fileObj.delete();
    }


    @Autowired
    private StorageService<FileStorageEntity> fileSystemStorageService;


    @Test
     void storeIsNullStream() throws StorageException{
        Throwable thrown = assertThrows(StorageException.class, () -> {
            fileSystemStorageService.store(null);
        });
    }

    @Test
    void storeIsNotNullStream() throws Exception {
//        String name = "test.mp3";
//
//        byte[] content = "testContent".getBytes();
////        File file = new File(name);
////
////
////
////        FileOutputStream fileOutputStream = new FileOutputStream(file);
////        fileOutputStream.write(content);
//
//        FileSystemStorageService mock =PowerMockito.spy(new FileSystemStorageService("D:\\\\songs" ));
//
//
//        Path path = Paths.get(name);
//
//    //    Path newPath = rootLocation.relativize(path);
//
//        try (InputStream is = new ByteArrayInputStream(content)){
//
//            PowerMockito.doReturn(path).when(mock, "createFilepath");
//            PowerMockito.doReturn(true).when(mock, "createFiles",is,path);
//
//
//            Assertions.assertEquals(fileSystemStorageService.store(is),path.toString());
//            Assertions.assertTrue(Files.exists(path));
//        }
//         catch (IOException | NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
    }

//    Path file = load(filename);
//    Resource resource = new UrlResource(file.toUri());
//            if (resource.exists() || resource.isReadable()) {
//        return resource;
//    }
//            else {
//        throw new StorageFileNotFoundException(
//                "Could not read file: " + filename);
//    }
//

    @Test
    void loadAsResource() throws IOException {
        String filename = "filename.mp3";
         fileObj = new File("D:\\songs\\"+filename);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileObj);
            fileOutputStream.write("test123".getBytes(StandardCharsets.UTF_8));

            Resource resource = fileSystemStorageService.loadAsResource(filename);

            Assertions.assertTrue(resource.exists());

            byte[] context = resource.getInputStream().readNBytes("test123".length());

            Assertions.assertArrayEquals(context,"test123".getBytes());
        }
        finally {
            fileObj.delete();
        }
    }


}