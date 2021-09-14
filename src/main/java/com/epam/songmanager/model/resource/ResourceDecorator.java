package com.epam.songmanager.model.resource;

import com.epam.songmanager.model.entity.StorageType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Transient;
import java.io.IOException;
import java.io.InputStream;

@Data
public abstract class ResourceDecorator  implements ResourceObj {

    private StorageType storageType;
    @Transient
    private InputStream stream;


    public ResourceDecorator(StorageType storageType) {
        this.storageType = storageType;
    }

    @Override
    public InputStream read() throws IOException {
        return stream;
    }

    public ResourceDecorator() {
    }

    public void save(InputStream stream) throws IOException {
        this.stream = stream;
    }
}
