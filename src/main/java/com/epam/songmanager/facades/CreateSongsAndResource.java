package com.epam.songmanager.facades;

import com.epam.songmanager.exceptions.CheckSumException;
import com.epam.songmanager.jms.Producer;
import com.epam.songmanager.model.entity.Resource;
import com.epam.songmanager.model.entity.Song;
import com.epam.songmanager.model.entity.StorageType;
import com.epam.songmanager.model.file_entity.ResourceDecorator;
import com.epam.songmanager.service.interfaces.AlbumService;
import com.epam.songmanager.service.interfaces.ResourceService;
import com.epam.songmanager.service.interfaces.SongService;
import com.epam.songmanager.service.interfaces.StorageService;
import com.epam.songmanager.service.parsers.AudioParser;
import com.epam.songmanager.utils.CheckSumImpl;
import com.epam.songmanager.utils.UnzipUtils;
import org.apache.commons.io.input.CountingInputStream;
import org.apache.commons.io.input.TeeInputStream;
import org.farng.mp3.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
@Scope("prototype")
public class CreateSongsAndResource<T extends ResourceDecorator> implements ObjInitializer<T> {
    @Autowired
    @Qualifier("messageDigestType")
    private  String messageDigest;
    @Autowired
    @Qualifier("fileExtension")
    private  String fileExtension;

    private  StorageService<T> storageService;

    public CreateSongsAndResource(StorageService<T> storageService) {
        this.storageService = storageService;
    }
    @Autowired
    private ResourceService resourceService;

    @Autowired
    private AudioParser mp3Parser;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private SongService songService;

    public CreateSongsAndResource() {

    }

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
        Resource resource =  new Resource( entity.getPath(),entity.getSize(),entity.getCheckSum(),
                StorageType.getTypeByClass(entity.getClass().getName()));
        initSong(resource,file);
        resourceService.addResource(resource);
    }


    @Override
    public void createFiles(InputStream stream,String filename) throws IOException, NoSuchAlgorithmException {
        if(UnzipUtils.isZip(filename)){
                List<ByteArrayInputStream> arrayOutputStreams =  UnzipUtils.getInputStreams((FileInputStream) stream);
                for (ByteArrayInputStream arrayOutputStream : arrayOutputStreams) {
                    createTmpAndMainFile(arrayOutputStream);
                }
    }
        else createTmpAndMainFile(stream);
    }

    private void  createTmpAndMainFile(InputStream stream) throws NoSuchAlgorithmException, IOException {

        File tmpFile = File.createTempFile("data", fileExtension);
        MessageDigest md = MessageDigest.getInstance(messageDigest);

        try (CountingInputStream is =new CountingInputStream(new DigestInputStream(
                new TeeInputStream(stream, new FileOutputStream(tmpFile)),md))){

            String path = storageService.store(is);
            String checkSumRes = CheckSumImpl.create(md);
            if(!resourceService.ifExistsByCheckSum(checkSumRes)){
                    initResource(storageService.create(checkSumRes,path,
                            is.getByteCount()),tmpFile);
            }
            else {
                throw  new CheckSumException("there is this song...");
            }

        } catch (CheckSumException e){
            tmpFile.delete();
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tmpFile.delete();
        }
    }

}
