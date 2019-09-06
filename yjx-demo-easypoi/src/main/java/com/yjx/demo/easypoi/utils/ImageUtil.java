package com.yjx.demo.easypoi.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: wuqing
 */
@Slf4j
public class ImageUtil {

  /**
   * 获取从网络上的图片的字节流
   */
  public static byte[] getFileByNetwork(String url) {
    try {
      URL urlConet = new URL(url);
      HttpURLConnection con = null;
      con = (HttpURLConnection) urlConet.openConnection();
      con.setRequestMethod("GET");
      con.setConnectTimeout(4 * 1000);
      //通过输入流获取图片数据
      InputStream inStream = con.getInputStream();
      ByteArrayOutputStream outStream = new ByteArrayOutputStream();
      byte[] buffer = new byte[2048];
      int len = 0;
      while ((len = inStream.read(buffer)) != -1) {
        outStream.write(buffer, 0, len);
      }
      inStream.close();
      byte[] data = outStream.toByteArray();
      return data;
    } catch (IOException e) {
      log.error("getFileByNetwork error. ", e);
    }
    return null;
  }

  /**
   * 获取从本地图片的字节流
   */
  public static byte[] getImageFromLocal(String url) {
    BufferedInputStream in = null;
    try {
      in = new BufferedInputStream(new FileInputStream(url));
      ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
      byte[] temp = new byte[2048];
      int size = 0;
      while ((size = in.read(temp)) != -1) {
        out.write(temp, 0, size);
      }
      in.close();
      byte[] content = out.toByteArray();
      return content;
    } catch (IOException e) {
      log.error("getImageFromLocal error. ", e);
    }
    return null;
  }




}
