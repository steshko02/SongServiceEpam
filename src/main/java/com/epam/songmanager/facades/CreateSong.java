package com.epam.songmanager.facades;


import com.epam.songmanager.config.properties.FileProperties;
import com.epam.songmanager.exceptions.FileParseException;
import com.epam.songmanager.model.entity.Resource;
import com.epam.songmanager.model.entity.Song;
import com.epam.songmanager.service.interfaces.AlbumService;
import com.epam.songmanager.service.interfaces.ResourceService;
import com.epam.songmanager.service.interfaces.SongService;
import com.epam.songmanager.service.interfaces.StorageSwitcher;
import com.epam.songmanager.service.parsers.AudioParser;
import io.minio.errors.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import org.farng.mp3.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@Transactional
@EnableConfigurationProperties(FileProperties.class)

public class CreateSong {

    private final  String fileExtension;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private AudioParser mp3Parser;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private SongService songService;

    @Autowired
    private StorageSwitcher serviceStorageSwitcher;

    @Autowired
    public CreateSong(FileProperties fileProperties) {
        this.fileExtension = fileProperties.getFileExtension();
    }


    @JmsListener(destination = "resources")
    @SendTo("zip")
    public String init(String message) throws Exception {
            Resource resource = resourceService.get(Long.valueOf(message));
            InputStream is = serviceStorageSwitcher.getByType(resource.getType()).
                    loadAsResource(resource.getPath()).getInputStream();

            createSong(resource, createTmpFileForParse(is));

            return "Resource " + message+ " parsed";
    }


    private  File createTmpFileForParse(InputStream stream) throws IOException {
        File tmpFile = File.createTempFile("data", fileExtension);
        try  {
            IOUtils.copy(stream,new FileOutputStream(tmpFile));
            stream.close();
            return  tmpFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            tmpFile.delete();
        }
        return null;
    }

    private void createSong(Resource resource, File tmp) throws Exception {
        try {
            Song song = new Song();
            song.setName(mp3Parser.getName(tmp));
            song.setAlbum(albumService.findByName(mp3Parser.getAlbum(tmp)));
            song.setNotes(mp3Parser.getNotes(tmp));
            song.setYear(mp3Parser.getYear(tmp));
            song.setResource(resource);
            songService.addSong(song);
        } catch (IOException | TagException e) {
            throw new Exception("Error: " + e);
        } catch (FileParseException e) {
            throw new FileParseException(AudioParser.class);
        }
    }
}