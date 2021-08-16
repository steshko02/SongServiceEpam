package com.epam.songmanager.utils;

import lombok.experimental.UtilityClass;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


@UtilityClass
public class UnzipUtils {

    private final static int BUFFER_SIZE = 2048;
    private final static String ZIP_EXTENSION = ".zip";
    private final static String MP3_EXTENSION = ".mp3";

    public static List<ByteArrayInputStream> getInputStreams(FileInputStream inputStream) throws IOException {

       BufferedInputStream bis = new BufferedInputStream(inputStream);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                BufferedOutputStream bos = new BufferedOutputStream(baos);

        byte[] buffer = new byte[BUFFER_SIZE];
        while (bis.read(buffer, 0, BUFFER_SIZE) != -1) {
            bos.write(buffer);
        }
        bos.flush();
        bos.close();
        bis.close();

        List<ByteArrayInputStream > is= new ArrayList<>();
        unzip(baos).forEach(b->is.add(new ByteArrayInputStream(b.toByteArray())));

        return is;

    }

    public static boolean isZip(String filename){
        return filename.matches(".*\\.zip(:|$).*");
    }

    public static List<ByteArrayOutputStream> unzip(ByteArrayOutputStream zippedFileOS) {
        try{

            ZipInputStream inputStream = new ZipInputStream(
                new BufferedInputStream(new ByteArrayInputStream(
                        zippedFileOS.toByteArray())),StandardCharsets.ISO_8859_1);

            ZipEntry entry;

            List<ByteArrayOutputStream> result = new ArrayList<>();
            while ((entry = inputStream.getNextEntry()) != null) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                int count;
                byte[] data = new byte[BUFFER_SIZE];
                if (!entry.isDirectory() && entry.getName().matches(".*\\"+MP3_EXTENSION+"(:|$).*")) {
                    BufferedOutputStream out = new BufferedOutputStream(
                            outputStream, BUFFER_SIZE);
                    while ((count = inputStream.read(data, 0, BUFFER_SIZE)) != -1) {
                        out.write(data, 0, count);
                    }
                    out.flush();
                    out.close();
                    if (entry.getName().toLowerCase().endsWith(ZIP_EXTENSION)) {
                        result.addAll(unzip(outputStream));
                    } else {
                        result.add(outputStream);
                    }
                }
            }
            inputStream.close();
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
