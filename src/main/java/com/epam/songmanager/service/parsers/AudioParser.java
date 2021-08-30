package com.epam.songmanager.service.parsers;

import com.epam.songmanager.exceptions.FileParseException;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.AbstractID3v2;

import java.io.File;
import java.io.IOException;

public interface AudioParser {

    String getName(File file) throws FileParseException, TagException, IOException;
    String getAlbum(File file) throws FileParseException, TagException, IOException;
    int getYear(File file) throws FileParseException, TagException, IOException;
    String getNotes(File file) throws FileParseException, TagException, IOException;


}
