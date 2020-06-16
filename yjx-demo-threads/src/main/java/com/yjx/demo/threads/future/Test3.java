package com.yjx.demo.threads.future;

import com.alibaba.fastjson.JSONObject;
import com.yjx.demo.threads.model.ThirdLogRequest;

public class Test3 {

    public static void main(String[] args) {
        ThirdLogRequest logRequest = new ThirdLogRequest();
        logRequest.setCZJG("11");
        System.out.println(JSONObject.toJSONString(logRequest));


    }

}
