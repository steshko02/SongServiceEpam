package com.epam.songmanager.service.impl;

import com.epam.songmanager.jms.listener.Listener;
import com.epam.songmanager.service.interfaces.ZipParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class ZipParserImpl implements ZipParser {

    @Autowired
    private Listener listener;

    @Override
    @JmsListener(destination = "zip")
    @SendTo("outbound.zip")
    public List<InputStream> parse(String path) {
        return null;
    }

}



//нужно вынести принятие inputSteam-a в сервис, и