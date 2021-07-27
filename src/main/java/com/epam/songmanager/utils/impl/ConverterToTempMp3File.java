package com.epam.songmanager.utils.impl;

import com.epam.songmanager.exceptions.StorageException;
import com.epam.songmanager.model.file_entity.BaseFile;
import com.epam.songmanager.model.file_entity.FileStorageEntity;
import com.epam.songmanager.utils.Converter;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;

import java.io.*;


@Service
public class ConverterToTempMp3File<T extends BaseFile>  implements Converter<T> {

    @Override
    public File converting(T entity) throws IOException {
        File tmpFile = File.createTempFile("data", ".mp3");

        try(OutputStream outputStream = new FileOutputStream(tmpFile)){
            IOUtils.copy(entity.getInputStream(), outputStream);
            entity.getInputStream().close();
        } catch (FileNotFoundException e) {
            throw new StorageException("Failed to find files", e);
        } catch (IOException e) {
            throw new StorageException("Failed to stored files", e);
        }
        return tmpFile;
    }
}
