package com.epam.songmanager.utils;

import org.farng.mp3.TagException;

import java.io.File;
import java.io.IOException;

public interface AudioParser {

    String getName(File multipartFile) throws IOException,  TagException;
    String getAlbum(File multipartFile)throws IOException,  TagException;
    int getYear(File multipartFile)throws IOException,  TagException;
    String getNotes(File multipartFile) throws IOException,  TagException;

}
