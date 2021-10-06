package com.epam.songmanager.model.storage.builder;


import com.epam.songmanager.model.resource.ResourceObj;

public interface ResourceBuilder {
  ResourceBuilder   withCompression();
  ResourceObj build();
}
