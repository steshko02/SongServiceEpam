package com.epam.songmanager.service;

import com.epam.songmanager.model.entity.StorageType;
import com.epam.songmanager.service.interfaces.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ServiceSwitcher {

    @Autowired
    @Qualifier("serviceHashMap")
    private Map<StorageType, StorageService<?>> map;


    public  StorageService<?> getByStorageType(StorageType storageType){
        return  map.get(storageType);
    }
}
