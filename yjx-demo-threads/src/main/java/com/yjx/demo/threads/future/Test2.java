package com.yjx.demo.threads.future;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test2 {

    /*public String readTextFromFile(File file) {
        //未捕获文件找不到的异常
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            //log.info("文件查找失败：" + e);
        }
        InputStreamReader reader = new InputStreamReader(fis);

        int count = 0;
        char[] buffer = new char[4096];
        StringBuffer result = new StringBuffer();

        while (true) {
            try {
                if (!((count = reader.read(buffer, 0, buffer.length)) != -1)){
                    break;
                }
            } catch (IOException e) {
                log.error("", e);
                break;
            }
            String readText = String.valueOf(buffer, 0, count);
            result.append(readText);
        }
        return result.toString();
    }*/

    public String reverseStr(String str){
        StringBuffer res = new StringBuffer();
        String lastWord = "";
        if(!(str.charAt(str.length()-1) >= 'a'  && str.charAt(str.length()-1) <= 'Z')){
            lastWord = str.substring(str.length()-1, str.length());
            str = str.substring(0, str.length()-1);
        }
        //res.c

        String[] words = str.split(" ");
        Stream.of(words).forEach(word -> {
            StringBuilder wordToHandle = new StringBuilder(word);
            res.append(wordToHandle.reverse()).append(" ");
        });
        return res.substring(0, res.length() - 1).concat(lastWord);
    }

    public static void main(String[] args) {
        Test2 test2 = new Test2();
       /* String s = test2.readTextFromFile(new File("E:\\\\test.txt"));
        System.out.println(s);*/
        System.out.println(test2.reverseStr(""));

    }
}
