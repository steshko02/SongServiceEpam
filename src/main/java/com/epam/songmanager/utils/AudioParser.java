package com.epam.songmanager.utils;

import org.farng.mp3.TagException;

import java.io.File;
import java.io.IOException;

public interface AudioParser {

    String getName(File file) throws IOException,  TagException;
    String getAlbum(File file)throws IOException,  TagException;
    int getYear(File file)throws IOException,  TagException;
    String getNotes(File file) throws IOException,  TagException;

}
