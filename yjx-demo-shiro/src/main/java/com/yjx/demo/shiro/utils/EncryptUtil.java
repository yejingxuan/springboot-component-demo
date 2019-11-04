package com.yjx.demo.shiro.utils;

import org.jasypt.util.text.BasicTextEncryptor;

public class EncryptUtil {

    public static String doEncrypt(String key, String words) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        //加密的key值，用于配置文件jasypt.encryptor.password的值
        encryptor.setPassword(key);
        //加密
        String encryptWords = encryptor.encrypt(words);
        return encryptWords;
    }


    public static String doDecrypt(String key, String words) {
        //解密
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(key);
        String decryptWords = encryptor.decrypt(words);
        return decryptWords;
    }

    public static void main(String[] args) {
        String encrypt = EncryptUtil.doEncrypt("", "");
        String decrypt = EncryptUtil.doDecrypt("", encrypt);
        System.out.printf("%s, %s", encrypt, decrypt);
    }

}
