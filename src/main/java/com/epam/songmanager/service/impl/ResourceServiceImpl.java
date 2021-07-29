package com.epam.songmanager.service.impl;

import com.epam.songmanager.model.Resource;
import com.epam.songmanager.repository.ResourceRepository;
import com.epam.songmanager.utils.CheckSum;
import com.epam.songmanager.service.ResourceService;
import org.farng.mp3.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class ResourceServiceImpl  implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private CheckSum checkSum;

    public Resource create(InputStream inputStream,String path,long size) throws NoSuchAlgorithmException, IOException, TagException {

        Resource resource = null;
        if(inputStream!=null ) {
            resource = new Resource();
            resource.setSize(size);
            var messageDigest = MessageDigest.getInstance("SHA-512"); //вынест в конфиг
            resource.setChecksum(checkSum.calculate(inputStream, messageDigest));
            resource.setPath(path);
        }
        return resource;
    }

    @Override
    public Long addResource(Resource resource) {
        if(!resourceRepository.existsByPath(resource.getPath())) {
            resourceRepository.save(resource);
            return resource.getId();
        }
        return null;
    }

    @Override
    public Resource get(Long id) {
        return  resourceRepository.getById(id);
    }

    @Override
    public List<Resource> getAll() {
        return resourceRepository.findAll();
    }

    @Override
    public void deleteAll() {
        resourceRepository.deleteAll();
    }



}
