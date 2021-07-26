package com.epam.songmanager.controllers;

import com.epam.songmanager.exceptions.StorageFileNotFoundException;
import com.epam.songmanager.repository.AlbumRepository;
import com.epam.songmanager.repository.SongRepository;
import com.epam.songmanager.service.ResourceService;
import com.epam.songmanager.service.SongService;
import com.epam.songmanager.service.StorageService;
import com.epam.songmanager.utils.AudioParser;
import org.farng.mp3.TagException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;

@Controller
public class FileUploadController {
    @Autowired
    private  StorageService storageService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private SongService songService;

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }



    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    AudioParser audioParser;

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws IOException, NoSuchAlgorithmException, TagException {
        File localFile = new File(storageService.store(file).toString());

       com.epam.songmanager.model.Resource resource = resourceService.create(localFile);

       songService.addSong(songService.create(audioParser.getName(localFile),audioParser.getYear(localFile),
               audioParser.getNotes(localFile), resource,
               albumRepository.findByName(audioParser.getAlbum(localFile))));
       // audioParser.getName();
//
//        songService.addSong(songService.create(name,1231,notes,resource,albumRepository.getById(7L)));

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}