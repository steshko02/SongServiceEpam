package com.epam.songmanager.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

public interface CheckSum {

    String calculate(InputStream stream, MessageDigest md) throws IOException;
}
