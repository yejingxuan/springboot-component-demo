package com.yjx.demo.quizzes.test;

import java.util.Date;
import java.util.Optional;
import lombok.Data;
import org.apache.http.client.utils.DateUtils;

public class TestStringHandle {

    public static void main(String[] args) {
        TestStringHandle testStringHandle = new TestStringHandle();
        //System.out.println(testStringHandle.test1("iot://12345678901234567890/123"));
        System.out.println(testStringHandle.formatStr(""));
    }

    private String formatStr(String s) {

        Item item = new Item();
        item.setAlbumName("布控库");
        item.setPhotoName("张三");
        item.setSfzh("421001199801012222");

        Item child = new Item();
        child.setSfzh("123");

        child.setChild(child);

        item.setChild(child);

        String albumName = Optional.ofNullable(item.getAlbumName()).orElse("default");
        String photoName = Optional.ofNullable(item.getPhotoName()).orElse("default");
        String sfzh = Optional.ofNullable(item.getChild()).map(Item::getSfzh).orElse("deflault");
        String cc_sfzh = Optional.ofNullable(item.getChild()).map(Item::getChild).map(Item::getSfzh).orElse("11");
        System.out.println(cc_sfzh);

        String captureDate = DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
        String videoName = "火车站1";
        String format = String.format("【靖江公安局】请注意，布控%s-%s(%s)%s在%S被抓拍识别，请予以处理。【靖江市公安局智慧人脸应用平台】",
                        albumName, photoName, sfzh, captureDate, videoName);
        return format;
    }


    private String test1(String url) {
        String regex = "iot://";
        if (url.startsWith(regex)) {
            int i = url.indexOf(regex) + regex.length();
            return url.substring(i, i + 20);
        } else {
            return null;
        }
    }

    @Data
    static class Item {
        private String albumName;
        private String photoName;
        private String sfzh;
        private Item child;
    }

}
