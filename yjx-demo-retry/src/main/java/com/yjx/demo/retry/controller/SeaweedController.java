package com.yjx.demo.retry.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import net.anumbrella.seaweedfs.core.FileTemplate;
import net.anumbrella.seaweedfs.core.file.FileHandleStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeaweedController {

    @Autowired
    private FileTemplate fileTemplate;

    @PostMapping(value = "/addFile")
    public FileHandleStatus addFile(){
        FileHandleStatus res = null;
        File file = new File("D:/test.jpg");
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            res = fileTemplate.saveFileByStream("photo2.jpg", is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

}
