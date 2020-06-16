package com.yjx.demo.threads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

public class Test1 {

    public static void main(String[] args) {
        Map<String, List<String>> systemIdMap = new HashMap<String, List<String>>();

        List<String> cameraIds = Arrays.asList("123", "456");

        Map<String, String> message = new HashMap<>();
        message.put("123", "");
        message.put("456", "2");

        for (String cameraId : cameraIds) {
            //根据cameraId查询到系统编码
            String sid = message.get(cameraId);
            if(systemIdMap.containsKey(sid)){
                systemIdMap.get(sid).add(cameraId);
            }else {
                ArrayList<String> sidCameraIds = new ArrayList<>();
                sidCameraIds.add(cameraId);
                systemIdMap.put(sid, sidCameraIds);
            }
        }

        System.out.println(systemIdMap);
        CopyOnWriteArraySet
    }

}
