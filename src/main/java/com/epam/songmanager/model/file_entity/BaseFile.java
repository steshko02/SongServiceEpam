package com.epam.songmanager.model.file_entity;

import lombok.Data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Data
public abstract class BaseFile {

    private InputStream inputStream;

    private String path;

    private long size;

    public BaseFile(InputStream inputStream, String path, long size) {
        this.inputStream = inputStream;
        this.path = path;
        this.size = size;
    }

    public InputStream getInputStreamClone() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        this.inputStream.transferTo(baos);
        this.inputStream = new ByteArrayInputStream(baos.toByteArray());
        return new ByteArrayInputStream(baos.toByteArray());//
    }
}
