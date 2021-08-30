package com.epam.songmanager.service.impl;

import com.amazonaws.util.IOUtils;
import com.epam.songmanager.config.properties.BucketProperties;
import com.epam.songmanager.exceptions.StorageException;
import com.epam.songmanager.model.entity.StorageType;
import com.epam.songmanager.model.resource.CloudStorageEntity;
import com.epam.songmanager.model.resource.ResourceDecorator;
import com.epam.songmanager.model.resource.ResourceObj;
import com.epam.songmanager.service.interfaces.StorageService;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
@EnableConfigurationProperties(BucketProperties.class)
public class MinioService implements StorageService<CloudStorageEntity> {

    private final  StorageType storageType = StorageType.CLOUD_SYSTEM;

    private final String bucketName ;

    @Autowired
    public MinioService( BucketProperties bucketProperties) {
        this.bucketName =bucketProperties.getLocation();
    }

    @Autowired
    private MinioClient minioClient;

    @Override
    public String store(InputStream is) throws IOException, NoSuchAlgorithmException, ServerException, InsufficientDataException, InternalException, InvalidResponseException, InvalidKeyException, XmlParserException, ErrorResponseException {

        if (is.available()==0) {
            throw new StorageException("Failed to store empty file.");
        }
        String  filename = createFileName();
        minioClient.putObject(
                PutObjectArgs.builder().bucket(bucketName).object(filename).stream(
                        is, is.available(), -1)
                        .build());
        is.close();
        return  filename;
    }

    private String createFileName(){
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        return uuidAsString+".mp3";
    }

    @Override
    public List<String> loadAll() {
        List<String> list = new LinkedList<>();
        Iterable<Result<Item>> results =
                minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).build());

        results.forEach(r-> {
            try {
                list.add(r.get().objectName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return list;
    }

    @Override
    public Path load(String filename)  {
            return  null;
    }

    @Override
    public Resource loadAsResource(String filename) throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InsufficientDataException, InternalException {
        try (InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(filename)
                        .build())) {
            return new ByteArrayResource(IOUtils.toByteArray(stream));
        }
        catch (IOException e){
            throw new IOException(e);
        }
    }

    @Override
    public void deleteAll() throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        List<DeleteObject> objects = new LinkedList<>();
        loadAll().forEach(o->objects.add(new DeleteObject(o)));

        Iterable<Result<DeleteError>> results =
                minioClient.removeObjects(
                        RemoveObjectsArgs.builder().bucket(bucketName).objects(objects).build());

        for (Result<DeleteError> result : results) {
                 result.get();
        }
    }

    @Override
    public CloudStorageEntity create(String cs, String path, long size) {
        return new CloudStorageEntity(path,size,cs);
    }

    @Override
    public Resource getResource(String filename) throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, InsufficientDataException, ErrorResponseException {
        try (InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(filename)
                        .build())) {
            return new ByteArrayResource(stream.readAllBytes());
        }
    }


    public boolean supports(StorageType resource) {
        return resource.equals(storageType);
    }
}
