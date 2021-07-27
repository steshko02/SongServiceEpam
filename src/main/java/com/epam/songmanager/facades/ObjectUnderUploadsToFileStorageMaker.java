package com.epam.songmanager.facades;

import com.epam.songmanager.model.Resource;
import com.epam.songmanager.model.Song;
import com.epam.songmanager.model.file_entity.BaseFile;
import com.epam.songmanager.model.file_entity.FileStorageEntity;
import com.epam.songmanager.service.ResourceService;
import com.epam.songmanager.service.StorageService;
import com.epam.songmanager.utils.AudioParser;
import com.epam.songmanager.utils.Converter;
import org.farng.mp3.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

@Service
public class ObjectUnderUploadsToFileStorageMaker <T extends BaseFile> implements ObjInitializer<T> {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private AudioParser mp3Parser;
    @Autowired
    private Converter<T> converterToTempFile;

    public void init(T entity) throws NoSuchAlgorithmException, TagException, IOException {

       Resource resource =  resourceService.create(entity.getInputStream(),entity.getPath(),entity.getSize());

        resourceService.addResource( resource);

        File file = converterToTempFile.converting(entity);

       String str= mp3Parser.getName(file);
        String str1= mp3Parser.getAlbum(file);
        String str2= mp3Parser.getNotes(file);
        int year= mp3Parser.getYear(file);


    }
//
//    private Song initSong(InputStream inputStream){
//
//    }

}
