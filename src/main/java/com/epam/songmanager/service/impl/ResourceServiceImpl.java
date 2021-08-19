package com.epam.songmanager.service.impl;

import com.epam.songmanager.exceptions.EntityNotFoundException;
import com.epam.songmanager.model.entity.Resource;
import com.epam.songmanager.model.entity.Song;
import com.epam.songmanager.repository.ResourceRepository;
import com.epam.songmanager.service.interfaces.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ResourceServiceImpl  implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

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

        Resource resource =resourceRepository.findById(id).orElse(null);
        if(resource == null){
            throw new EntityNotFoundException(Resource.class, "id", id.toString());
        }
        return  resource;
    }

    @Override
    public List<Resource> getAll() {
        return resourceRepository.findAll();
    }

    @Override
    public void deleteAll() {
        resourceRepository.deleteAll();
    }

    @Override
    public boolean ifExistsByCheckSum(String str) {
        return resourceRepository.existsByChecksum(str);
    }


}
