package com.epam.songmanager.repository;

import com.epam.songmanager.model.entity.StorageType;
import com.epam.songmanager.model.resource.ResourceDecorator;
import com.epam.songmanager.model.resource.ResourceObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResourceObjRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public ResourceObj getResource(StorageType storageType ){

        Query q = new Query();
        q.addCriteria(Criteria.where("storageType").is(storageType));

      return mongoTemplate.findOne(q, ResourceObj.class,"fileStorageEntity");
    }
}