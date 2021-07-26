package com.epam.songmanager.service.impl;

import com.epam.songmanager.model.Resource;
import com.epam.songmanager.repository.ResourceRepository;
import com.epam.songmanager.service.CheckSum;
import com.epam.songmanager.service.ResourceService;
import com.epam.songmanager.service.SongService;
import com.epam.songmanager.utils.AudioParser;
import org.farng.mp3.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class ResourceServiceImpl  implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private CheckSum checkSum;
    @Autowired
    private AudioParser audioParser;
    public Resource create( File file ) throws NoSuchAlgorithmException, IOException, TagException {

        audioParser.getName(file);

        Resource resource = null;
        if(file.exists() && !file.isDirectory() ) {
            resource = new Resource();
            resource.setSize(file.length());
            var messageDigest = MessageDigest.getInstance("SHA-512");
            resource.setChecksum(checkSum.calculate(file.getPath(), messageDigest));
            resource.setPath(file.getPath());
        }
        return resource;
    }

    @Override
    public void addResource(Resource resource) {
        if(!resourceRepository.existsByPath(resource.getPath())) {
            resourceRepository.save(resource);
        }
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
