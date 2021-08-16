package com.epam.songmanager.model.file_entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter

public class FileStorageEntity extends ResourceDecorator {


    public FileStorageEntity(String checkSumRes, String path, long length) {
        super(checkSumRes,path,length);
    }

//    @Override
//    public void save(InputStream is) throws IOException, NoSuchAlgorithmException {
//        try (InputStream  targetInputStream = new DigestInputStream(is,MessageDigest.getInstance("SHA-512"))) {
//                super.save(targetInputStream);
//        } catch (IOException e) {
//            throw new IOException("Exception occurred while encrypting or stream resource handling. ", e);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public InputStream read() throws IOException {
        try {
            return super.read();
        } catch (IOException e) {
            throw new IOException("Exception occurred while decompressing input stream. ", e);
        }
    }

//    @Override
//    public ResourceObj save(ResourceObj resourceObj) throws IOException {
//        return null;
//    }

}
