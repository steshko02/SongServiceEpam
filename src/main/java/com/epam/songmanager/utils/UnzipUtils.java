package com.epam.songmanager.utils;

import lombok.experimental.UtilityClass;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@UtilityClass
public class UnzipUtils {

    private final static int BUFFER_SIZE = 2048;
    private final static String ZIP_EXTENSION = ".zip";
    private final static String MP3_EXTENSION = ".mp3";


    public static boolean isZip(String filename) {
        return filename.matches(".*\\.zip(:|$).*");
    }

    public static void unzip(InputStream zipInputStream, Consumer<ByteArrayOutputStream> callback) {
        try {
            ZipInputStream inputStream = new ZipInputStream(
                    new BufferedInputStream(new ByteArrayInputStream(
                            zipInputStream.readAllBytes())), StandardCharsets.ISO_8859_1);
            ZipEntry entry;
            while ((entry = inputStream.getNextEntry()) != null) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                int count;
                byte[] data = new byte[BUFFER_SIZE];
                   if (!entry.isDirectory() && (entry.getName().toLowerCase().endsWith(MP3_EXTENSION))
                           || entry.getName().toLowerCase().endsWith(ZIP_EXTENSION)) {
                       if (!entry.isDirectory()) {
                           BufferedOutputStream out = new BufferedOutputStream(
                                   outputStream, BUFFER_SIZE);
                           while ((count = inputStream.read(data, 0, BUFFER_SIZE)) != -1) {
                               out.write(data, 0, count);
                           }
                               callback.accept(outputStream);
                           out.flush();
                           out.close();
                       }
                   }

            }
            inputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
