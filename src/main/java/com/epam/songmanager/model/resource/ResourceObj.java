package com.epam.songmanager.model.resource;

import java.io.IOException;
import java.io.InputStream;


public interface ResourceObj {
    InputStream read() throws IOException;
    void save(InputStream stream) throws IOException;
}
