package com.epam.songmanager.service.parsers;

import org.farng.mp3.TagException;

import java.io.File;
import java.io.IOException;

public interface AudioParser {

    String getName();
    String getAlbum();
    int getYear();
    String getNotes();

    void create(File file) throws IOException, TagException;

}
