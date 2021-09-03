package com.epam.songmanager.service;

import com.epam.songmanager.model.resource.ResourceObj;
import com.epam.songmanager.service.interfaces.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Primary
@Service
public class ResourceServiceImpl implements ResourceService{
    private Map<Class<? extends ResourceObj>,StorageService> resourceServiceMap = new HashMap<>();

    @Autowired
    public ResourceServiceImpl(List<StorageService> services) {
        services.stream().forEach(s->resourceServiceMap.put(s.getSupportsClass(),s));
    }

    private InputStream get(ResourceObj res){
       return resourceServiceMap.get(res.getClass()).read();
    }

}
