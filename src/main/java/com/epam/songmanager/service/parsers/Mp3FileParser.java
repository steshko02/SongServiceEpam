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
    private  AbstractID3v2 tags;

    public void create(File file) throws IOException, TagException, FileParseException {
        MP3File mp3File  = new MP3File(file);
        if(mp3File.hasID3v2Tag()) {
            tags= mp3File.getID3v2Tag();
        }
        else throw new FileParseException(Mp3FileParser.class);
    }

    @Override
    public String getName() throws FileParseException {
        String name =  tags.getSongTitle();
        if(name == null|| name.isEmpty()){
            throw new FileParseException(Mp3FileParser.class);
        }
        return name;
    }

    @Override
    public String getAlbum() throws FileParseException {
        String album = tags.getAlbumTitle();
        if(album == null || album.isEmpty()){
            throw new FileParseException(Mp3FileParser.class);
        }
        return album;
    }

    @Override
    public int getYear() throws FileParseException {
        String year = tags.getYearReleased();
        if(year == null || year.isEmpty()){
            throw new FileParseException(Mp3FileParser.class);
        }
        return Integer.parseInt(year);
    }

    @Override
    public String getNotes() throws FileParseException {
        String notes = tags.getSongComment();
        if(notes == null|| notes.isEmpty()){
            throw new FileParseException(Mp3FileParser.class);
        }
        return notes;
    }
}
