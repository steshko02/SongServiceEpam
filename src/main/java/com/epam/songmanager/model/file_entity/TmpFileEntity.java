package com.epam.songmanager.model.file_entity;

import org.apache.commons.io.input.TeeInputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TmpFileEntity extends ResourceDecorator{



    private final File tmpFile = File.createTempFile("data",".mp3");

    public TmpFileEntity() throws IOException {
    }

    @Override
    public void save(InputStream is) throws IOException, NoSuchAlgorithmException {
        try (InputStream  targetInputStream = new TeeInputStream(is,new FileOutputStream(tmpFile))) {
            super.save(targetInputStream);
        } catch (IOException e) {
            throw new IOException("Exception occurred while encrypting or stream resource handling. ", e);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public InputStream read() throws IOException {
        try {
            return super.read();
        } catch (IOException e) {
            throw new IOException("Exception occurred while decompressing input stream. ", e);
        }
    }

    @Override
    public ResourceObj save(ResourceObj resourceObj) throws IOException {
        return null;
    }

}
