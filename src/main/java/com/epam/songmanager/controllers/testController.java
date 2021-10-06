package com.epam.songmanager.controllers;

import com.epam.songmanager.jms.Producer;
import com.epam.songmanager.repository.mango.ResourceRepository;
import com.epam.songmanager.repository.mango.StorageRepository;
import com.epam.songmanager.service.impl.GZIPCompressionOperations;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.*;


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
