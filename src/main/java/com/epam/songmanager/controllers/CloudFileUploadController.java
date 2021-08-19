package com.epam.songmanager.controllers;

import com.epam.songmanager.exceptions.FileParseException;
import com.epam.songmanager.facades.ObjInitializer;
import com.epam.songmanager.model.file_entity.CloudStorageEntity;
import com.epam.songmanager.service.interfaces.StorageService;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Controller
public class CloudFileUploadController {

    @Autowired
    private StorageService<CloudStorageEntity> storageService;
    @Autowired
    private ObjInitializer<CloudStorageEntity> objInitializer;

    @GetMapping("/s3")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files",storageService.loadAll());
        return "s3UploadForm";
    }

    @PostMapping("/s3")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws Exception, FileParseException {
        objInitializer.createFiles(file.getInputStream(),file.getOriginalFilename());
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/s3";
    }

    @GetMapping("/s3/delAll")
    public String deleteAll() throws IOException, ServerException, InsufficientDataException, NoSuchAlgorithmException, InternalException, InvalidResponseException, XmlParserException, InvalidKeyException, ErrorResponseException {
        storageService.deleteAll();
        return "redirect:/s3";
    }

    @GetMapping("/s3/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InsufficientDataException, InternalException {

        Resource resource = storageService.loadAsResource(filename);

        return ResponseEntity
                .ok()
                .contentLength(resource.contentLength())
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + filename + "\"")
                .body(resource);
    }


}
