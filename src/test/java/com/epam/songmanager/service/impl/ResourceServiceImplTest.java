package com.epam.songmanager.service.impl;

import com.epam.songmanager.model.entity.Resource;
import com.epam.songmanager.model.entity.StorageType;
import com.epam.songmanager.repository.ResourceRepository;
import com.epam.songmanager.service.interfaces.ResourceService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ResourceServiceImplTest {

    @MockBean
    private ResourceRepository resourceRepository;
    @Autowired
    private ResourceService resourceService;


//    @Test
//    void addResourceWhenNotExists() {
//        Resource resource = new Resource(1L,"path",99,"checkSum", StorageType.DISK_FILE_SYSTEM);
//        Mockito.when(resourceRepository.existsByChecksum(resource.getChecksum())).thenReturn(false);
//        Long id = resourceService.addResource(resource);
//        Mockito.verify(resourceRepository,Mockito.times(1)).save(resource);
//        assertFalse(resourceRepository.existsByChecksum(resource.getChecksum()));
//        assertEquals(id,1L);
//
//    }
//    @Test
//    void addResourceWhenIsExists() {
//        Resource resource = new Resource("path",99,"checkSum", StorageType.DISK_FILE_SYSTEM);
//        Long id = resourceService.addResource(resource);
//        Mockito.when(resourceRepository.existsByChecksum(resource.getChecksum())).thenReturn(true);
//        assertTrue(resourceRepository.existsByChecksum(resource.getChecksum()));
//        assertNull(id);
//    }
//
//    @Test
//    void get() {
//        Mockito.when(resourceRepository.getById(1L)).thenReturn(new Resource("path",99,"checkSum", StorageType.DISK_FILE_SYSTEM));
//        Resource resource = resourceService.get(1L);
//        Mockito.verify(resourceRepository,Mockito.times(1)).getById(1L);
//        assertEquals(resource,new Resource("path",99,"checkSum", StorageType.DISK_FILE_SYSTEM));
//
//    }
//
//    @Test
//    void getAll() {
//        List<Resource> resources = new ArrayList<>();
//        resources.add(new Resource("path1",99,"checkSum1", StorageType.DISK_FILE_SYSTEM));
//        resources.add(new Resource("path1",100,"checkSum1", StorageType.DISK_FILE_SYSTEM));
//        Mockito.when(resourceRepository.findAll()).thenReturn(resources);
//        assertEquals(resources,resourceService.getAll());
//        Mockito.verify(resourceRepository,Mockito.times(1)).findAll();
//    }

    @Test
    void deleteAll() {
        resourceRepository.deleteAll();
        Mockito.verify(resourceRepository,Mockito.times(1)).deleteAll();
    }

    @Test
    void ifExistsByCheckSumIsExist() {
        String cs="checkSum";
        Mockito.when(resourceRepository.existsByChecksum(cs)).thenReturn(true);
        assertTrue(resourceService.ifExistsByCheckSum(cs));
        Mockito.verify(resourceRepository,Mockito.times(1)).existsByChecksum(cs);
    }

    @Test
    void ifExistsByCheckSumIsNoyExist() {
        String cs="checkSum";
        Mockito.when(resourceRepository.existsByChecksum(cs)).thenReturn(false);
        assertFalse(resourceService.ifExistsByCheckSum(cs));
        Mockito.verify(resourceRepository,Mockito.times(1)).existsByChecksum(cs);
    }
}