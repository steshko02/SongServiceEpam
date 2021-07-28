package com.epam.songmanager.utils;

import java.io.IOException;
import java.io.InputStream;

public interface InpStreamClone {
    InputStream getClone(InputStream stream) throws IOException;
}
