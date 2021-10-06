package com.epam.songmanager.jms;

import com.epam.songmanager.facades.CreateSong;
import com.epam.songmanager.model.resource.ResourceObj;
import com.epam.songmanager.repository.mango.ResourceObjRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
@Slf4j
public class Listener {

    @Autowired
    private ResourceObjRepository resourceObjRepository;

    @Autowired
    private CreateSong createSong;

    @JmsListener(destination = "resources")
    @SendTo("zip")
    public String init(String message) throws Exception {
       ResourceObj resource = resourceObjRepository.getResourceById(message);
        createSong.saveSong(resource);
        return message;
    }
}