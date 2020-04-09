package com.yjx.demo.quizzes.test;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test1 implements Serializable {


    private static final long serialVersionUID = 1194925268379687245L;
    private static SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy.MM.dd");

    public static void main(String[] args) {
        System.out.println(getCurrentDayendTimeMillis());
        System.out.println(new Date(getCurrentDayendTimeMillis()));
        System.out.println(new Date(0));
    }

    public static long getDay(long timestamp) {
        long day = 1000 * 3600 * 24;
        long eightHour = 1000 * 3600 * 8;
        return (timestamp + eightHour) / day; // 时间戳0时间为1970/1/1 8:0:0，要补8小时再除
    }

    public static long getCurrentDayendTimeMillis() {
        long day = 1000 * 3600 * 24;
        return getDay(System.currentTimeMillis()) * day + day/3*2-1;
    }


    public void test2() {
        String userPortraitPath = "http://10.231.50.14:8080/v5/faces/45-AAABcNMgvO6UOVglAAAAAQ==/image/full";
        String serviceIp = "10.231.50.178";

        String handleStr = userPortraitPath.replace("http://", "")
                .replace("8080", "10080");

        String res = "https://".concat(serviceIp)
                .concat(handleStr.substring(handleStr.indexOf(":")));
        System.out.println(res);
    }

    public void test1() {
        long l = System.currentTimeMillis();

        Date date = new Date(l);
        String format = dateFormat2.format(date);

        System.out.println(format);
    }

}
