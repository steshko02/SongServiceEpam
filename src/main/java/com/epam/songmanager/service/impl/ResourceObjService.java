package com.epam.songmanager.service.impl;

import com.epam.songmanager.model.entity.StorageType;
import com.epam.songmanager.model.resource.ResourceObj;
import com.epam.songmanager.repository.ResourceObjRepository;
import com.epam.songmanager.service.interfaces.CreateFileSwitcher;
import com.epam.songmanager.service.interfaces.ResourceObjectService;
import com.epam.songmanager.service.interfaces.StorageService;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Primary
public class ResourceObjService implements ResourceObjectService {

    private Map<Class<? extends ResourceObj>,StorageService> resourceServiceMap = new HashMap<>();

    @Autowired
    private ResourceObjRepository resourceObjRepository;
    @Autowired
    private CreateFileSwitcher createFilesSwitcher;

    public ResourceObj getRes(StorageType storageType){
      return resourceObjRepository.getResource(storageType);
    }

    @Autowired
    public ResourceObjService(List<StorageService> services) {
        services.stream().forEach(s->resourceServiceMap.put(s.getSupportsClass(),s));
    }

    public void store(InputStream inputStream,StorageType storageType,String ex) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        ResourceObj resourceObj = resourceObjRepository.getResource(storageType);
        resourceObj.save(inputStream);
        createFilesSwitcher.getByType(storageType).createFiles(resourceObj,ex);
    }
    private StorageService getService (StorageType storageType) throws IOException {
        ResourceObj resourceObj = resourceObjRepository.getResource(storageType);
        return resourceServiceMap.get(resourceObj.getClass());
    }

    public  List<String> loadAll(StorageType storageType) throws IOException {
        return getService(storageType).loadAll();
    }

    public Path load(StorageType storageType,String filename) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        return getService(storageType).load(filename);
    }

    public Resource loadAsResource(StorageType storageType,String filename) throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InsufficientDataException, InternalException {
        return getService(storageType).loadAsResource(filename);
    }


}
