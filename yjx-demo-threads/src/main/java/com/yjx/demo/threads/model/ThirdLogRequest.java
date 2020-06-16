package com.yjx.demo.threads.model;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import lombok.Data;

/**
 * @author yufei
 * @description 日志推送数据
 * @create 2020-02-04 18:43
 **/
@Data
public class ThirdLogRequest implements Serializable {

    /**
     * 系统主键(320800+2位序号)
     */
    private String XTID;

    /**
     * 系统名称
     */
    private String XTMC;

    /**
     * 用户账号
     */
    private String YHZH;

    /**
     * 用户姓名
     */
    private String YHXM;

    /**
     * 用户警号
     */
    private String YHJH;

    /**
     * 用户身份证号
     */
    private String YHSFZH;

    /**
     * 用户单位名称
     */
    private String YHDWMC;

    /**
     * 用户单位代码
     */
    private String YHDWDM;

    /**
     * 终端地址(IP或移动终端mac)
     */
    private String ZDDZ;

    /**
     * 操作类型(0 登录 1 查询 2 新增 3 修改 4 删除)
     */
    private String CZLX;

    /**
     * 操作时间 YYYYMMDDHHMISS
     */
    private String CZSJ;

    /**
     * 操作结果(1 成功 0 失败)
     */
    @JSONField(name = "CZJG")
    private String CZJG;

    /**
     * 业务系统日志主键
     */
    private String YWXTRZID;

    /**
     * 日志主键
     */
    private String RZID;

    /**
     * 模块主键
     */
    private String MKID;

    /**
     * 模块名称
     */
    private String MKMC;

    /**
     * 操作条件
     */
    private String CZTJ;

    /**
     * 操作内容
     */
    private String CZNR;

    /**
     * 失败原因
     */
    private String SBYY;

}
