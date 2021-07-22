package com.epam.songmanager.service;

import java.io.IOException;
import java.security.MessageDigest;

public interface CheckSum {

    String calculate(String filename, MessageDigest md) throws IOException;
}
