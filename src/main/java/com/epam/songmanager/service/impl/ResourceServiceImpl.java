package com.epam.songmanager.service.impl;

import com.epam.songmanager.model.Resource;
import com.epam.songmanager.repository.ResourceRepository;
import com.epam.songmanager.service.CheckSum;
import com.epam.songmanager.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class ResourceServiceImpl  implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private CheckSum checkSum;

    public void addResource(String path) throws NoSuchAlgorithmException, IOException {
        File file = new File(path);
        if(file.exists() && !file.isDirectory()) {
            Resource resource = new Resource();
            resource.setSize(file.length());
            var messageDigest = MessageDigest.getInstance("SHA-512"); // или "SHA-512"
            resource.setChecksum(checkSum.calculate(path, messageDigest));
            resource.setPath(path);
            resourceRepository.save(resource);
        }
    }

    @Override
    public Resource get(Long id) {
        return  resourceRepository.getById(id);
    }

}
