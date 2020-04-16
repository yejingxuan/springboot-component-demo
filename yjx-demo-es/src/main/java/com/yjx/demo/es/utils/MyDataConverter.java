package com.yjx.demo.es.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.beanutils.Converter;


public class MyDataConverter implements Converter {


    @Override
    public <T> T convert(Class<T> aClass, Object o) {
        String dateStr = (String)o;
        SimpleDateFormat spdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = spdt.parse(dateStr);
            return (T)date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
