package com.epam.songmanager.facades;

import com.epam.songmanager.exceptions.CheckSumException;
import com.epam.songmanager.jms.Producer;
import com.epam.songmanager.model.entity.Resource;
import com.epam.songmanager.model.entity.StorageType;
import com.epam.songmanager.model.resource.ResourceDecorator;
import com.epam.songmanager.model.resource.ResourceObj;
import com.epam.songmanager.service.interfaces.ResourceService;
import com.epam.songmanager.service.interfaces.StorageService;
import com.epam.songmanager.utils.CheckSumImpl;
import com.epam.songmanager.utils.UnzipUtils;
import org.apache.commons.io.IOUtils;
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
public class CreateResourceService<T extends ResourceDecorator> implements CreateResource<T> {

    private  String messageDigest;
    private  StorageService<T> storageService;

    public CreateResourceService(StorageService<T> storageService, String messageDigest) {
        this.storageService = storageService;
        this.messageDigest = messageDigest;
    }

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private  Producer producer;

    public CreateResourceService() {
    }

    private void initResource(ResourceObj resourceObj,String path, Long size, String checkSum)  {
        Resource resource =  new Resource( path,size,checkSum,
                StorageType.getTypeByClass(resourceObj.getClass().getName()));

        resourceService.addResource(resource);
        producer.sendMessage(resource.getId().toString());
    }

    @Override
    public void createFiles(ResourceObj stream, String filename) throws IOException, NoSuchAlgorithmException {
        try(InputStream stream1 = stream.read()) {
            if(UnzipUtils.isZip(filename)){
                UnzipUtils.unzip((FileInputStream) stream1,x-> {
                    try {
                        stream.save(new ByteArrayInputStream(x.toByteArray()));
                        tryCreateTmpAndMainFile(stream);
                    } catch (NoSuchAlgorithmException | IOException e) {
                        e.printStackTrace();
                    }
                });
            }
            else tryCreateTmpAndMainFile(stream);
        }
    }

    @Override
    public boolean supports(StorageType storageType) {
        return storageService.supports(storageType);
    }

    private void  tryCreateTmpAndMainFile(ResourceObj resourceObj) throws NoSuchAlgorithmException{



        MessageDigest md = MessageDigest.getInstance(messageDigest);

        try (   InputStream inputStream = resourceObj.read();
                CountingInputStream is =new CountingInputStream(new DigestInputStream(inputStream,md))){

            String path = storageService.store(is);
            String checkSumRes = CheckSumImpl.create(md);
            if(!resourceService.ifExistsByCheckSum(checkSumRes)){
                initResource(resourceObj,path,is.getByteCount(), checkSumRes);
            }
            else {
                throw  new CheckSumException("there is this song...");
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

}