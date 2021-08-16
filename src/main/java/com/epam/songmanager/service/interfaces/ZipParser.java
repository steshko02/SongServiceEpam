package com.epam.songmanager.service.interfaces;

import java.io.InputStream;
import java.util.List;

public interface ZipParser {
    List<InputStream> parse (String path);
}
