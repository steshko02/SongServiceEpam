package com.epam.songmanager.service.impl;

import com.epam.songmanager.facades.CreateResource;
import com.epam.songmanager.model.entity.StorageType;
import com.epam.songmanager.service.interfaces.CreateFileSwitcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateFileSwitcherImpl implements CreateFileSwitcher {

    @Autowired
    private List<CreateResource> createResources;

    @Override
    public CreateResource<?> getByType(StorageType storageType) {
        return createResources.stream().filter(s -> s.supports(storageType)).
                findAny().
                orElseThrow(null);
    }
}
