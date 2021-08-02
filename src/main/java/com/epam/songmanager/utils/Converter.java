package com.epam.songmanager.utils;

import java.io.File;
import java.io.IOException;



public interface Converter <T> {

    File converting (T entity) throws IOException;
    boolean delete (T entity) throws IOException;
    void write (int b) throws IOException;
}
