package com.epam.songmanager.facades;

import com.epam.songmanager.exceptions.FileParseException;
import com.epam.songmanager.model.entity.Resource;
import com.epam.songmanager.model.entity.Song;
import com.epam.songmanager.service.ServiceSwitcher;
import com.epam.songmanager.service.interfaces.AlbumService;
import com.epam.songmanager.service.interfaces.ResourceService;
import com.epam.songmanager.service.interfaces.SongService;
import com.epam.songmanager.service.parsers.AudioParser;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import org.farng.mp3.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@Transactional
public class CreateResource {

    @Autowired
    @Qualifier("fileExtension")
    private  String fileExtension;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private AudioParser mp3Parser;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private SongService songService;

    @Autowired
    private ServiceSwitcher serviceSwitcher;

        @JmsListener(destination = "resources")
        @SendTo("zip")
        public String init(String message)  {
            try {
                Resource resource = resourceService.get(Long.valueOf(message));
                InputStream is = serviceSwitcher.getByStorageType(resource.getType()).
                        loadAsResource(resource.getPath()).getInputStream();

                createSong(resource, createTmpFileForParse(is));

                return "Resource " + message+ " parsed";
            } catch ( Exception e) {
                e.printStackTrace();
            }
            return null;
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

        private void createSong(Resource resource, File tmp) throws Exception, FileParseException {
            try {
                Song song = new Song();
                mp3Parser.create(tmp);
                song.setName(mp3Parser.getName());
                song.setAlbum(albumService.findByName(mp3Parser.getAlbum()));
                song.setNotes(mp3Parser.getNotes());
                song.setYear(mp3Parser.getYear());
                song.setResource(resource);
                songService.addSong(song);
            } catch (IOException | TagException e) {
                throw new Exception("Error: " + e);
            } catch (FileParseException e) {
                throw new FileParseException(AudioParser.class);
            }
        }
}
