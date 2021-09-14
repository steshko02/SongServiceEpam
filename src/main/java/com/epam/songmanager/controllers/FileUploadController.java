package com.epam.songmanager.controllers;

import com.epam.songmanager.exceptions.StorageFileNotFoundException;
import com.epam.songmanager.model.entity.StorageType;
import com.epam.songmanager.service.impl.ResourceObjService;
import com.epam.songmanager.service.interfaces.CreateFileSwitcher;
import com.epam.songmanager.service.interfaces.ResourceObjectService;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
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
@RequiredArgsConstructor
public class FileUploadController {

    @Autowired
    private ResourceObjectService resourceObjService;


    @SneakyThrows
    @GetMapping("/")
    public String listUploadedFiles(@NotNull Model model, @RequestParam(required=false,name ="st") StorageType storageType) throws IOException {

        if(storageType == null)
            storageType = StorageType.DISK_FILE_SYSTEM;

        model.addAttribute("storage", storageType);
        model.addAttribute("files", resourceObjService.loadAll(storageType));
        return "uploadForm";
    }

    @GetMapping("/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename,
                                              @RequestParam("st") StorageType storageType) throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, InsufficientDataException, ErrorResponseException {

        Resource file = resourceObjService.loadAsResource(storageType,filename);
        return ResponseEntity
                .ok()
                .contentLength(file.contentLength())
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + filename + "\"")
                .body(file);
    }

     @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   @RequestParam("st") StorageType storageType,
                                   RedirectAttributes redirectAttributes) throws Exception {

         resourceObjService.store(file.getInputStream(),storageType,file.getOriginalFilename());

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/?st="+storageType.toString();
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}