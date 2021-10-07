package com.epam.songmanager.facades;


import com.epam.songmanager.config.properties.FileProperties;
import com.epam.songmanager.exceptions.CheckSumException;
import com.epam.songmanager.exceptions.FileParseException;
import com.epam.songmanager.model.entity.Resource;
import com.epam.songmanager.model.entity.Song;
import com.epam.songmanager.model.resource.ResourceObj;
import com.epam.songmanager.model.storage.Storage;
import com.epam.songmanager.repository.mango.StorageRepository;
import com.epam.songmanager.service.interfaces.AlbumService;
import com.epam.songmanager.service.interfaces.ResourceService;
import com.epam.songmanager.service.interfaces.SongService;
import com.epam.songmanager.service.parsers.AudioParser;
import com.epam.songmanager.service.parsers.Mp3Metadata;
import com.epam.songmanager.utils.CheckSumImpl;
import com.epam.songmanager.utils.UnzipUtils;
import org.apache.commons.io.input.CountingInputStream;
import org.farng.mp3.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

@Service
@Transactional
@EnableConfigurationProperties(FileProperties.class)
public class CreateSong {
    private final String messageDigest = "MD5";

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private AudioParser mp3Parser;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private SongService songService;

    @Autowired
    private StorageRepository storageRepository;

    public  void  saveSong(ResourceObj resourceObj){
        try(BufferedInputStream stream = new BufferedInputStream(resourceObj.read())) {
            if(UnzipUtils.isZip(stream)){
                Storage storage = storageRepository.getStorageById(resourceObj.getStorageId());
                UnzipUtils.unzip(stream, x-> {
                    ResourceObj innerResource = storage.requestBuilder().withCompression().build();
                    try {
                        innerResource.save(new ByteArrayInputStream(x.toByteArray()));
                        createSong(innerResource);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                resourceObj.delete();
            }else{
                createSong(resourceObj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Resource createResource(InputStream stream,String path) throws Exception {
        MessageDigest md = MessageDigest.getInstance(messageDigest);
        try ( BufferedInputStream bis = new BufferedInputStream(stream);
                CountingInputStream is =new CountingInputStream(new DigestInputStream(bis,md))){
            String checkSumRes = CheckSumImpl.create(md);
            if(!resourceService.ifExistsByCheckSum(checkSumRes)){
               return new Resource(path,is.getByteCount(),checkSumRes);
            }
            else {
                throw  new CheckSumException("there is this song...");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void createSong(ResourceObj resourceObj) throws Exception {
        try(InputStream stream = resourceObj.read()) {
            Song song = new Song();
            song.setResource(createResource(stream,resourceObj.getPath()));
            Mp3Metadata metadata = mp3Parser.getMetadata(resourceObj.read());
            song.setName(metadata.getName());
            song.setAlbum(albumService.findByName(metadata.getAlbum()));
            song.setNotes(metadata.getNotes());
            song.setYear(metadata.getYear());
            song.setResourceObjId(resourceObj.getId());
            songService.addSong(song);
        } catch (IOException | TagException e) {
            throw new Exception("Error: " + e);
        } catch (FileParseException e) {
            throw new FileParseException(AudioParser.class);
        }
    }
}
