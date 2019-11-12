package com.yjx.demo.quickssm.utils;

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
        //String encrypt = EncryptUtil.doEncrypt("jingxuan", "123456");
        String decrypt = EncryptUtil.doDecrypt("jingxuan", "LFQiXbl5MMgccHsAsJmm7Q==");
        System.out.printf("%s, %s", "LFQiXbl5MMgccHsAsJmm7Q==", decrypt);
    }
}
