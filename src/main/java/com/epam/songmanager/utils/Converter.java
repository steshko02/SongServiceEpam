package com.epam.songmanager.utils;

import java.io.File;
import java.io.IOException;

public interface Converter <T> {

    File converting (T entity) throws IOException;
}
