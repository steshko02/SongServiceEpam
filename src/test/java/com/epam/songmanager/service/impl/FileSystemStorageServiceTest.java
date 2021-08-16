package com.epam.songmanager.service.impl;

import com.epam.songmanager.exceptions.StorageException;
import com.epam.songmanager.model.file_entity.FileStorageEntity;
import com.epam.songmanager.service.interfaces.StorageService;
import io.minio.errors.*;
import org.junit.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import static org.junit.Assert.*;


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
    void loadAsResource() throws IOException, ServerException, ErrorResponseException, NoSuchAlgorithmException, InsufficientDataException, InternalException, InvalidResponseException, XmlParserException, InvalidKeyException {
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