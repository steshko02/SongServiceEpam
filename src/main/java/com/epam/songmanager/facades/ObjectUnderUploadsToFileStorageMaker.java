package com.epam.songmanager.facades;

import com.epam.songmanager.model.Resource;
import com.epam.songmanager.model.Song;
import com.epam.songmanager.model.file_entity.BaseFile;
import com.epam.songmanager.service.AlbumService;
import com.epam.songmanager.service.ResourceService;
import com.epam.songmanager.service.SongService;
import com.epam.songmanager.utils.AudioParser;
import com.epam.songmanager.utils.Converter;
import org.farng.mp3.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.NoSuchAlgorithmException;

@Service
public class ObjectUnderUploadsToFileStorageMaker <T extends BaseFile> implements ObjInitializer<T> {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private AudioParser mp3Parser;
    @Autowired
    private Converter<T> converterToTempFile;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private SongService songService;


    private void initSong(T entity,Resource resource) throws Exception {
        File file = null;
        try {
            Song song = new Song();
            file = converterToTempFile.converting(entity);
            mp3Parser.create(file);
            song.setName(mp3Parser.getName());
            song.setAlbum(albumService.findByName(mp3Parser.getAlbum()));
            song.setNotes(mp3Parser.getNotes());
            song.setYear(mp3Parser.getYear());

            file.delete();
            song.setResource(resource);
            songService.addSong(song);
        }
        catch (IOException | TagException e){
            throw new Exception("Error: " + e);
        }

        finally {
            file.delete();
        }
    }

    public void init(T entity) throws Exception {

        Resource resource =  resourceService.
                create(entity.getInputStreamClone(),entity.getPath(),entity.getSize());

        resourceService.addResource( resource);

        entity.setInputStream(entity.getInputStreamClone());

        initSong(entity,resource);

    }


}
