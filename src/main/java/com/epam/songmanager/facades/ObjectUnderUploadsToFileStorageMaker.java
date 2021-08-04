package com.epam.songmanager.facades;

import com.epam.songmanager.model.entity.Resource;
import com.epam.songmanager.model.entity.Song;
import com.epam.songmanager.model.file_entity.FileStorageEntity;
import com.epam.songmanager.model.file_entity.ResourceDecorator;
import com.epam.songmanager.service.AlbumService;
import com.epam.songmanager.service.ResourceService;
import com.epam.songmanager.service.SongService;
import com.epam.songmanager.service.StorageService;
import com.epam.songmanager.utils.AudioParser;
import com.epam.songmanager.utils.CheckSum;
import com.epam.songmanager.utils.Converter;
import org.apache.commons.io.input.CountingInputStream;
import org.apache.commons.io.input.TeeInputStream;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.farng.mp3.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class ObjectUnderUploadsToFileStorageMaker <T extends ResourceDecorator> implements ObjInitializer<T> {


    @Value("${MessageDigest}")
    private  String messageDigest;

    @Value("${FileExtension}")
    private  String fileExtension;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    StorageService<T> storageService;

    @Autowired
    private AudioParser mp3Parser;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private SongService songService;

    @Autowired
    private CheckSum checkSum ;


    private void initSong(Resource resource,File tmp) throws Exception {

        try {
            Song song = new Song();
            mp3Parser.create(tmp);
            song.setName(mp3Parser.getName());
            song.setAlbum(albumService.findByName(mp3Parser.getAlbum()));
            song.setNotes(mp3Parser.getNotes());
            song.setYear(mp3Parser.getYear());

            song.setResource(resource);
            songService.addSong(song);
        }
        catch (IOException | TagException e){
            throw new Exception("Error: " + e);
        }
    }

    private void initResource(T entity, File file) throws Exception {

        Resource resource =  resourceService.
                create(entity.getCheckSum(),entity.getPath(), entity.getSize());
        initSong(resource,file);
        resourceService.addResource(resource);
    }

    @Override
    public void  createFiles(InputStream stream) throws NoSuchAlgorithmException, IOException {

        File tmpFile = File.createTempFile("data", fileExtension);
        MessageDigest md = MessageDigest.getInstance(messageDigest);

        try (CountingInputStream is =new CountingInputStream(new DigestInputStream(
                new TeeInputStream(stream, new FileOutputStream(tmpFile)),md))){

            String path = storageService.store(is);
            String checkSumRes = checkSum.create(md);
            if(!resourceService.ifExistsByCheckSum(checkSumRes)){
                    initResource(storageService.create(checkSumRes,path,
                            is.getByteCount()),tmpFile);
            }else {
                is.close();
                Files.delete(Path.of(path));
            }
           tmpFile.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            tmpFile.delete();
        }
    }
}
