package com.epam.songmanager.service.parsers;

import com.epam.songmanager.exceptions.FileParseException;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.AbstractID3v2;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface AudioParser {

//    String getName(InputStream stream) throws FileParseException, TagException, IOException;
//    String getAlbum(InputStream stream) throws FileParseException, TagException, IOException;
//    int getYear(InputStream stream) throws FileParseException, TagException, IOException;
//    String getNotes(InputStream stream) throws FileParseException, TagException, IOException;

      Mp3Metadata getMetadata(InputStream stream)  throws TagException, FileParseException, IOException;

}
