package com.epam.songmanager.utils.impl;

import com.epam.songmanager.utils.AudioParser;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.AbstractID3v2;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Service
public class AudioParserImpl implements AudioParser {

    private AbstractID3v2 getTags(File file) throws IOException, TagException {
        MP3File mp3File  = new MP3File(file);
        if(mp3File.hasID3v2Tag()) {
            return mp3File.getID3v2Tag();
        }
        return  null;
    }

    @Override
    public String getName(File multipartFile) throws IOException, TagException {
        return Objects.requireNonNull(getTags(multipartFile)).getSongTitle();
    }

    @Override
    public String getAlbum(File multipartFile) throws IOException, TagException {
        return Objects.requireNonNull(getTags(multipartFile)).getAlbumTitle();
    }

    @Override
    public int getYear(File multipartFile) throws IOException, TagException {
        return Integer.parseInt(Objects.requireNonNull(getTags(multipartFile)).getYearReleased());
    }

    @Override
    public String getNotes(File multipartFile) throws IOException, TagException {
        return Objects.requireNonNull(getTags(multipartFile)).getSongComment();
    }
}
