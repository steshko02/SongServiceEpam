package com.epam.songmanager.controllers;

import com.epam.songmanager.model.resource.CloudStorageEntity;
import com.epam.songmanager.service.interfaces.StorageService;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Controller
public class CloudFileUploadController {

    @Autowired
    private StorageService<CloudStorageEntity> storageService;

    @GetMapping("/s3/delAll")
    public String deleteAll() throws IOException, ServerException, InsufficientDataException, NoSuchAlgorithmException, InternalException, InvalidResponseException, XmlParserException, InvalidKeyException, ErrorResponseException {
        storageService.deleteAll();
        return "redirect:/s3";
    }
}
