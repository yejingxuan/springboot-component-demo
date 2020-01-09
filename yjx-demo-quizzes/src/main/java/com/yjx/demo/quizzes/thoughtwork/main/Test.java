package com.yjx.demo.quizzes.thoughtwork.main;

import com.yjx.demo.quizzes.thoughtwork.model.Mon;
import com.yjx.demo.quizzes.thoughtwork.model.Ticket;
import com.yjx.demo.quizzes.thoughtwork.service.ProxyBuyService;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        //test.buyTicket();
        test.sendGet();
    }



    public static CloseableHttpResponse sendGet(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://www.zhipin.com/");
        httpGet.setConfig(
                RequestConfig.custom().setConnectTimeout(3000).setConnectionRequestTimeout(30000)
                        .setSocketTimeout(30000).build());
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            return httpResponse;
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (httpResponse != null) {
                    httpResponse.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static void buyTicket(){
        System.out.println("I am busy ,plase xiaoming proxy");
        ProxyBuyService proxyBuyService = new ProxyBuyService();
        AtomicReference<Ticket> ticket = new AtomicReference<>(new Ticket());
        proxyBuyService.proxyBuy(ticketProxy -> {
            ticket.set(ticketProxy);
        });
        System.out.println(ticket.get());
    }


    public String method4(){
        System.out.println(Thread.currentThread().getName());
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("start " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("expection");
            }
            System.out.println("end " + Thread.currentThread().getName());
        });
        try {
            future.get(4000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    public static void method3() {
        String idCard = "11101000000000111X";
        String REGEX_ID_CARD = "\\d{15}(\\d{2}[0-9X])?";
        String REGEX_ID_CARD2 = "\\d{17}([0-9X])?";

        System.out.println(Pattern.matches(REGEX_ID_CARD, idCard));
        System.out.println(Pattern.matches(REGEX_ID_CARD2, idCard));
    }

    public static void method1() {
        String fileName = "张三_男性_在逃人员_1994.jpg";
        System.out.println(fileName.substring(0, fileName.lastIndexOf(".")));
    }

    public static void method2() {
        List<Mon> mons = Arrays.asList(new Mon("001", 1), new Mon("002", 1), new Mon("003", 0));
        System.out.println(mons);

        Map<Integer, List<Mon>> group = mons.stream()
                .collect(Collectors.groupingBy(Mon::getResource));
        System.out.println(group);
    }

}
