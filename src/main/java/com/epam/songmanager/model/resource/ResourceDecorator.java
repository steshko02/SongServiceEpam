package com.epam.songmanager.model.resource;

import com.epam.songmanager.model.entity.StorageType;
import io.minio.errors.*;
import lombok.Data;

import javax.persistence.Transient;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Data
public abstract class ResourceDecorator  implements ResourceObj {

    private StorageType storageType;
    @Transient
    private ResourceObj resourceObj;

    public ResourceDecorator(ResourceObj resourceObj) {
        this.resourceObj = resourceObj;
    }

    public ResourceDecorator(StorageType storageType) {
        this.storageType = storageType;
    }

    @Override
    public InputStream read() throws IOException {
        return this.resourceObj.read();
    }

    public ResourceDecorator() {
    }

    public void save(InputStream stream) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        resourceObj.save(stream);
    }
}
