package com.epam.songmanager.service.impl;

import com.epam.songmanager.config.StorageProperties;
import com.epam.songmanager.exceptions.StorageException;
import com.epam.songmanager.exceptions.StorageFileNotFoundException;
import com.epam.songmanager.facades.ObjInitializer;
import com.epam.songmanager.model.file_entity.FileStorageEntity;
import com.epam.songmanager.service.ResourceService;
import com.epam.songmanager.service.StorageService;
import com.epam.songmanager.utils.CheckSum;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService<FileStorageEntity> {

    private final Path rootLocation; //изменить на String

   @Autowired
   private CheckSum checkSum ;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ObjInitializer<FileStorageEntity> objInitializer;


    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    public String store(InputStream stream) throws IOException, NoSuchAlgorithmException {

        if (stream == null) {
            throw new StorageException("Failed to store empty file.");
        }
        Path path =createFilepath();
        createFiles(stream,path.toString());
        return  path.toString();
    }

    private Path createFilepath(){
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        Path destinationFile = Paths.get(uuidAsString+".mp3");
        return rootLocation.relativize(destinationFile);
    }

    private void createFiles(InputStream stream, String path) throws IOException, NoSuchAlgorithmException {
        File file = new File(path);
        OutputStream os = new FileOutputStream(file);
        try {
            IOUtils.copy(stream,os);
        }
        catch (IOException e){
            throw new IOException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);
            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public FileStorageEntity create(String cs, String path, long size) {
        return new FileStorageEntity(cs,path,size);
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    public FileSystemStorageService(Path rootLocation) {
        this.rootLocation = rootLocation;
    }

}