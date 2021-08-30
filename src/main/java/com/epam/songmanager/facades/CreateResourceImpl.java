package com.epam.songmanager.facades;

import com.epam.songmanager.exceptions.CheckSumException;
import com.epam.songmanager.jms.Producer;
import com.epam.songmanager.model.entity.Resource;
import com.epam.songmanager.model.entity.StorageType;
import com.epam.songmanager.model.resource.ResourceDecorator;
import com.epam.songmanager.service.interfaces.ResourceService;
import com.epam.songmanager.service.interfaces.StorageService;
import com.epam.songmanager.utils.CheckSumImpl;
import com.epam.songmanager.utils.UnzipUtils;
import org.apache.commons.io.input.CountingInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Scope("prototype")
public class CreateResourceImpl<T extends ResourceDecorator> implements CreateResource<T> {


    private  String messageDigest;
    private  StorageService<T> storageService;

    public CreateResourceImpl(StorageService<T> storageService, String messageDigest) {
        this.storageService = storageService;
        this.messageDigest = messageDigest;
    }

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private  Producer producer;

    public CreateResourceImpl() {
    }

    private void initResource(T entity)  {
        Resource resource =  new Resource( entity.getPath(),entity.getSize(),entity.getCheckSum(),
                StorageType.getTypeByClass(entity.getClass().getName()));

        resourceService.addResource(resource);
        producer.sendMessage(resource.getId().toString());
    }

    @Override
    public void createFiles(InputStream stream,String filename) throws IOException, NoSuchAlgorithmException {
        if(UnzipUtils.isZip(filename)){
            List<ByteArrayInputStream> arrayOutputStreams =  UnzipUtils.getInputStreams((FileInputStream) stream);
            for (ByteArrayInputStream arrayOutputStream : arrayOutputStreams) {
                createTmpAndMainFile(arrayOutputStream);
            }
        }
        else createTmpAndMainFile(stream);
    }

    @Override
    public boolean supports(StorageType storageType) {
        return storageService.supports(storageType);
    }

    private void  createTmpAndMainFile(InputStream stream) throws NoSuchAlgorithmException{

        MessageDigest md = MessageDigest.getInstance(messageDigest);

        try (CountingInputStream is =new CountingInputStream(new DigestInputStream(stream,md))){

            String path = storageService.store(is);
            String checkSumRes = CheckSumImpl.create(md);
            if(!resourceService.ifExistsByCheckSum(checkSumRes)){
                initResource(storageService.create(checkSumRes,path, is.getByteCount()));
            }
            else {
                throw  new CheckSumException("there is this song...");
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

}