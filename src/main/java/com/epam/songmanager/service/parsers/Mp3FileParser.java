package com.epam.songmanager.service.parsers;

import com.epam.songmanager.exceptions.FileParseException;
import lombok.Data;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.AbstractID3v2;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@Data
public class Mp3FileParser implements AudioParser {

    private AbstractID3v2 create(File file) throws IOException, TagException, FileParseException {
        MP3File mp3File  = new MP3File(file);
        if(mp3File.hasID3v2Tag()) {
         return mp3File.getID3v2Tag();
        }
        else throw new FileParseException(Mp3FileParser.class);
    }

    @Override
    public String getName(File file) throws FileParseException, TagException, IOException {
        String name = create(file).getSongTitle();
        if(name == null|| name.isEmpty()){
            throw new FileParseException(Mp3FileParser.class);
        }
        return name;
    }

    @Override
    public String getAlbum(File file) throws FileParseException, TagException, IOException {
        String album = create(file).getAlbumTitle();
        if(album == null || album.isEmpty()){
            throw new FileParseException(Mp3FileParser.class);
        }
        return album;
    }

    @Override
    public int getYear(File file) throws FileParseException, TagException, IOException {
        String year = create(file).getYearReleased();
        if(year == null || year.isEmpty()){
            throw new FileParseException(Mp3FileParser.class);
        }
        return Integer.parseInt(year);
    }

    @Override
    public String getNotes(File file) throws FileParseException, TagException, IOException {
        String notes = create(file).getSongComment();
        if(notes == null || notes.isEmpty()){
            throw new FileParseException(Mp3FileParser.class);
        }
        return notes;
    }
}
