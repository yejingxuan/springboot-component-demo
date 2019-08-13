package com.yjx.demo.activity.utils;

import com.yjx.demo.activity.model.RespMsg;

public class RespUtil {

    public static RespMsg success(Object data){
        RespMsg respMsg = new RespMsg();
        respMsg.setCode("success");
        respMsg.setData(data);
        return respMsg;
    }

    public static RespMsg success(){
        RespMsg respMsg = new RespMsg();
        respMsg.setCode("success");
        return respMsg;
    }

}
