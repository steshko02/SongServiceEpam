package com.epam.songmanager.service.parsers;

import com.epam.songmanager.exceptions.FileParseException;
import org.farng.mp3.TagException;

import java.io.File;
import java.io.IOException;

public interface AudioParser {

    String getName() throws FileParseException;
    String getAlbum() throws FileParseException;
    int getYear() throws FileParseException;
    String getNotes() throws FileParseException;

    void create(File file) throws IOException, TagException, FileParseException;

}
