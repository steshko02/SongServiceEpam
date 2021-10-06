package com.epam.songmanager.service.impl;

import com.epam.songmanager.service.interfaces.CompressionOperation;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPCompressionOperations implements CompressionOperation {

    @Override
    public InputStream compressInputStream(InputStream inputStreamToCompress) throws IOException {
        try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(new BufferedOutputStream(
                new ByteArrayOutputStream()))) {

            byte[] buffer = new byte[1024];
            int    len;
            while ((len = inputStreamToCompress.read(buffer)) != -1) {
                gzipOutputStream.write(buffer, 0, len);
            }
//порешать со стримом
            IOUtils.copy(inputStreamToCompress, gzipOutputStream);

            return inputStreamToCompress;
        }
    }

    @Override
    public OutputStream compressOutputStream(OutputStream outputStreamToCompress) throws IOException {
        return new GZIPOutputStream(new BufferedOutputStream(outputStreamToCompress));

    }

    @Override
    public InputStream decompressInputStream(InputStream inputStreamToDecompress)
            throws IOException {
        return new GZIPInputStream(inputStreamToDecompress);
    }

}
