package com.epam.songmanager.service;

import com.epam.songmanager.model.entity.Resource;
import org.farng.mp3.TagException;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface ResourceService  {
    Long  addResource(Resource resource);
    Resource get(Long id);
    List<Resource> getAll();
    Resource  create(String checkSum,String path,long size) throws NoSuchAlgorithmException, IOException, TagException;
    void deleteAll();
    boolean  ifExistsByCheckSum(String str);
}
