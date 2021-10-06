package com.epam.songmanager.controllers;

import com.epam.songmanager.jms.Producer;
import com.epam.songmanager.model.entity.StorageType;
import com.epam.songmanager.model.entity.builder.ResourceBuilder;
import com.epam.songmanager.model.entity.builder.ResourceBuilderImpl;
import com.epam.songmanager.model.resource.FileStorageEntity;
import com.epam.songmanager.model.resource.ResourceObj;
import com.epam.songmanager.model.resource.decoration.CompressionResource;
import com.epam.songmanager.model.storage.FSStorage;
import com.epam.songmanager.model.storage.MinioStorage;
import com.epam.songmanager.model.storage.Storage;
import com.epam.songmanager.repository.mango.ResourceRepository;
import com.epam.songmanager.repository.mango.StorageRepository;
import com.epam.songmanager.service.impl.GZIPCompressionOperations;
import com.epam.songmanager.service.impl.ResourceObjService;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPOutputStream;


@Controller
public class testController {

//    @Autowired
//    private ResourceObjService resourceObjService;
//
    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    private Producer producer;

    @Autowired
    private ResourceRepository resourceRepository;

    @SneakyThrows
    @GetMapping("/test4")
    public String  testSong1() throws IOException {

        GZIPCompressionOperations operations= new GZIPCompressionOperations();

        File file = new File("C:\\Users\\Admin\\Desktop\\Linkin Park - In The End.mp3");

        InputStream stream1 = new FileInputStream(file);

        InputStream stream2 = new FileInputStream(file);

        InputStream compStream = operations.compressInputStream(stream1);

        System.out.println("--------------------------------------");
        System.out.println(stream2.readAllBytes().length);
        System.out.println(operations.decompressInputStream(compStream).readAllBytes().length);
        System.out.println("--------------------------------------");
        return "audio";
    }

    @GetMapping("/test")
    public String  test() {

        return  null;
    }


}
