package com.epam.songmanager.model.entity.builder;


import com.epam.songmanager.model.resource.ResourceObj;

public interface ResourceBuilder {
  ResourceBuilder withUnzip();
  ResourceBuilder   withCompression();
  ResourceObj build();
}
