package com.epam.songmanager.service.impl;

import com.epam.songmanager.model.entity.StorageType;
import com.epam.songmanager.service.interfaces.StorageService;
import com.epam.songmanager.service.interfaces.StorageSwitcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceStorageSwitcher implements StorageSwitcher {

    @Autowired
    private List<StorageService<?>> services;

    @Override
    public StorageService<?> getByType(StorageType resource) {
        return services.stream().filter(s -> s.supports(resource)).
                findAny().
                orElseThrow(null);
    }
}
