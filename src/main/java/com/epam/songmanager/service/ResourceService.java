package com.epam.songmanager.service;

import com.epam.songmanager.model.Resource;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface ResourceService  {
    void addResource(String path) throws NoSuchAlgorithmException, IOException;
    Resource get(Long id);
}
