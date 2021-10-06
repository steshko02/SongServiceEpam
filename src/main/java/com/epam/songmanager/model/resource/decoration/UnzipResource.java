package com.epam.songmanager.model.resource.decoration;

import com.epam.songmanager.model.resource.ResourceDecorator;
import com.epam.songmanager.model.resource.ResourceObj;
import io.minio.errors.*;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class UnzipResource extends ResourceDecorator {

    public UnzipResource(ResourceObj delegate) {
        super(delegate);
    }

    @Override
    public InputStream read() throws IOException {
        return  super.read();
    }

    @Override
    public void save(InputStream stream) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        super.save(stream);
    }

    //выпилиьб
    @Override
    public void setStorageId(String storageId) {

    }

    @Override
    public String  getId() {
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
