package com.epam.songmanager.service.impl;

import com.epam.songmanager.config.StorageProperties;
import com.epam.songmanager.exceptions.StorageException;
import com.epam.songmanager.exceptions.StorageFileNotFoundException;
import com.epam.songmanager.model.file_entity.FileStorageEntity;
import com.epam.songmanager.service.ResourceService;
import com.epam.songmanager.service.StorageService;
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
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService<FileStorageEntity> {

    private final Path rootLocation; //изменить на String

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    public FileStorageEntity store(InputStream stream) {

        if (stream == null) {
            throw new StorageException("Failed to store empty file.");
        }
        return   createFileStorageFile(stream,createFilepath());
    }

    private String createFilepath(){
        String destinationFile;
        destinationFile = this.rootLocation.toString();
        if (!destinationFile.equals(this.rootLocation.toAbsolutePath().toString())) {
            throw new StorageException("Cannot store file outside current directory.");
        }
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        destinationFile += "\\"+uuidAsString+".mp3";
        return destinationFile;
    }

    private FileStorageEntity  createFileStorageFile(InputStream stream,String path){
        File file =  new File(path);
        try(OutputStream outputStream = new FileOutputStream(file)){
            IOUtils.copy(stream, outputStream);
            return new FileStorageEntity(stream,path,file.getTotalSpace());
        } catch (FileNotFoundException e) {
            throw new StorageException("Failed to find files", e);
        } catch (IOException e) {
            throw new StorageException("Failed to stored files", e);
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
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }




}