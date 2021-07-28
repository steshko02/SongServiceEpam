package com.epam.songmanager.utils.impl;

import com.epam.songmanager.utils.InpStreamClone;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class InpStreamCloneImpl implements InpStreamClone {
    @Override
    public InputStream getClone(InputStream stream) throws IOException {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        stream.transferTo(baos);
//        return new ByteArrayInputStream(baos.toByteArray());
        return  null;
    }
}
