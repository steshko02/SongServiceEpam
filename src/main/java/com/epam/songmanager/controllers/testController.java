package com.epam.songmanager.controllers;

import com.epam.songmanager.model.entity.StorageType;
import com.epam.songmanager.model.resource.ResourceObj;
import com.epam.songmanager.service.impl.ResourceObjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class testController {

    @Autowired
    private ResourceObjService resourceObjService;

    @GetMapping("/test4")
    public String  testSong1() {
        return "audio";
    }

    @GetMapping("/test")
    public String  test() {
        StorageType storageType = StorageType.DISK_FILE_SYSTEM;
        ResourceObj resourceObj = resourceObjService.getRes(storageType);
        return resourceObj.toString();
    }
}
