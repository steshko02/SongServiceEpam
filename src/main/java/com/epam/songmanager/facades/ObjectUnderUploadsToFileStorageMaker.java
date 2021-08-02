package com.epam.songmanager.facades;

import com.epam.songmanager.model.entity.Resource;
import com.epam.songmanager.model.entity.Song;
import com.epam.songmanager.model.file_entity.BaseFile;
import com.epam.songmanager.service.AlbumService;
import com.epam.songmanager.service.ResourceService;
import com.epam.songmanager.service.SongService;
import com.epam.songmanager.service.StorageService;
import com.epam.songmanager.utils.AudioParser;
import com.epam.songmanager.utils.CheckSum;
import com.epam.songmanager.utils.Converter;
import org.farng.mp3.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.MessageDigest;

@Service
public class ObjectUnderUploadsToFileStorageMaker <T extends BaseFile> implements ObjInitializer<T> {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private StorageService<T> storageService;
    @Autowired
    private AudioParser mp3Parser;
    @Autowired
    private Converter<T> converterToTempFile;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private SongService songService;

    @Autowired
    private CheckSum checkSum;

    private  void  storeInpStream(InputStream inputStream ) throws IOException {
        storageService.store(inputStream);
    }

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

        String sum  = checkSum.calculate(entity.getInputStream(), MessageDigest.getInstance("SHA-512"));

        entity.setInputStream(entity.getInputStreamClone());

        if(resourceService.ifExistsByCheckSum(sum)){
            return;
        }


        Resource resource =  resourceService.
                create(checkSum.calculate(entity.getInputStream(),
                        MessageDigest.getInstance("SHA-512")),entity.getPath(),entity.getSize());

        initSong(entity,resource);
        resourceService.addResource(resource);
    }


}
