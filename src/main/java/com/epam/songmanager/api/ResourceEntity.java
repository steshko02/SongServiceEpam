package com.epam.songmanager.api;

import java.io.InputStream;

public interface ResourceEntity {
    void save(InputStream is) ;
    InputStream read();
}
