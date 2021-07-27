package com.epam.songmanager.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {
    private String location = "C:\\Users\\stesh\\OneDrive\\Рабочий стол\\songs";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}