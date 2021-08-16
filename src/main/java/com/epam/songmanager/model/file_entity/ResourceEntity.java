package com.epam.songmanager.model.file_entity;

import java.io.InputStream;

public interface ResourceEntity {
    void save(InputStream is) ;
    InputStream read();
}
