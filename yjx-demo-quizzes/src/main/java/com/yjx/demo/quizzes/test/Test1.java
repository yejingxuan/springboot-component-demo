package com.yjx.demo.quizzes.test;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Data;

@Data
public class Test1 implements Serializable {


    private static final long serialVersionUID = 1194925268379687245L;
    private static SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy.MM.dd");
    private final static Pattern IP_REGEX = Pattern.compile("(\\d+\\.\\d+\\.\\d+\\.\\d+)");
    private final static Pattern PORT_REGEX = Pattern.compile("\\:(\\d+)");

    private final static String[] defaultIpAndPort = {"0.0.0.0","80"};

    private Boolean a;


    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(1);

        /*System.out.println(getCurrentDayendTimeMillis());
        System.out.println(new Date(getCurrentDayendTimeMillis()));
        System.out.println(new Date(0));*/
        //testBool();
        //testMatch();
        testMap();
    }


    public static void testMap() {
        Map<String, String> alreadySend = new ConcurrentHashMap<>();
        alreadySend.put("123"+null, "123");
        alreadySend.put("123"+null, "456");
        System.out.println(alreadySend.toString());
    }

    public static void testMatch(){
        String ip = "0.0.0.0";
        String location = "aa54.12.03.11";
        Matcher matcher = IP_REGEX.matcher(location);
        if(ip.equals(defaultIpAndPort[0]) && matcher.find()){
            ip = matcher.group(1);
        }
        System.out.println(ip);
    }

    public static void testBool(){
        Test1 test1 = new Test1();
        if(test1.getA()){
            System.out.println("true");
        }
        System.out.println("false");
    }

    public static void testPattern () {
        Pattern DEVICE_ID_PATTERN = Pattern.compile("^([0-9]{20})$");
        System.out.println(DEVICE_ID_PATTERN.matcher("12345678901234567890").matches());
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
