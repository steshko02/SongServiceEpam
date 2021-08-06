package com.epam.songmanager.controllers;

import com.epam.songmanager.facades.ObjInitializer;
import com.epam.songmanager.model.file_entity.FileStorageEntity;
import com.epam.songmanager.service.StorageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(FileUploadController.class)
public class FileUploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StorageService<FileStorageEntity> storageService;

    @MockBean
    private ObjInitializer<FileStorageEntity> objInitializer;

    @Test
    public void uploadsFile() throws Exception {

        String fileName = "sample-file-mock.txt";
        MockMultipartFile sampleFile = new MockMultipartFile(
                "uploaded-file",
                fileName,
                "text/plain",
                "This is the file content".getBytes());

        MockMultipartHttpServletRequestBuilder multipartRequest =
                MockMvcRequestBuilders.multipart("/");

        mockMvc.perform(multipartRequest.file(sampleFile));

    }

    @Test
    void listUploadedFiles() throws Exception {

        List<Path> paths = new ArrayList<>();
        paths.add(Path.of("test1"));
        paths.add(Path.of("test2"));
        paths.add(Path.of("test3"));

        Stream<Path> pathStream = paths.stream();

        Mockito.when(storageService.loadAll()).thenReturn(pathStream);

//        var c = storageService.loadAll().map(
//                        path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
//                                "serveFile", path.getFileName().toString()).build().toUri().toString())
//                        .collect(Collectors.toList());
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
//                .andExpect(model().attribute("files", equalTo(c)))
        .andExpect(view().name("uploadForm"));
    }



    @Test
    void serveFile() throws Exception {

        String filename = "filename.mp3";
        File fileObj = new File(filename);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileObj);
            fileOutputStream.write("test123".getBytes(StandardCharsets.UTF_8));

            Mockito.when(storageService.load(filename)).thenReturn(Path.of(filename));

            Path file = storageService.load(filename);
            Resource resource = new UrlResource(file.toUri());

            Mockito.when(storageService.loadAsResource(filename)).thenReturn(resource);

            mockMvc.perform(
                    get("/files/filename.mp3"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("test123"));
        }
        catch (Exception e){
            throw new  Exception ("test Exception" + e);
        }
        finally {
            fileObj.delete();
        }

    }

}