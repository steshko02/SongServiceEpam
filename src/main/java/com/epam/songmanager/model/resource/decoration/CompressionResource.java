package com.epam.songmanager.model.resource.decoration;

import com.epam.songmanager.model.resource.ResourceDecorator;
import com.epam.songmanager.model.resource.ResourceObj;
import com.epam.songmanager.service.impl.GZIPCompressionOperations;
import com.epam.songmanager.service.interfaces.CompressionOperation;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;

public class CompressionResource extends ResourceDecorator {

    private  CompressionOperation compressionOperation;

    public CompressionResource( ResourceObj delegate) {
        super(delegate);
        compressionOperation = new GZIPCompressionOperations();
    }

    @SneakyThrows
    @Override
    public void save(InputStream is) throws IOException {
        try (   InputStream isToUse = is;
                InputStream compressedIs = compressionOperation.compressInputStream(isToUse)) {
            super.save(compressedIs);
        }
    }

    @Override
    public InputStream read() throws IOException {
        try {
            return compressionOperation.decompressInputStream(super.read());
        } catch (IOException e) {
           throw new IOException("failed read InputStream");
        }
    }

    @Override
    public void setStorageId(String storageId) {
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getStorageId() {
        return null;
    }

    @Override
    public String getPath() {
        return null;
    }

    @Override
    public void delete() {

    }

    @Override
    public void setPath(String path) {

    }

    @Override
    public Class<? extends ResourceObj> supports() {
        return null;
    }

    @Override
    public String getFileName() {
        return null;
    }
}
