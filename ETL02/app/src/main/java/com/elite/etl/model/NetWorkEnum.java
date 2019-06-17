package com.elite.etl.model;

/**
 * @Author: Wesker
 * @Date: 2019-05-28 10:24
 * @Version: 1.0
 */
public enum NetWorkEnum {
    /**
     * 数据网络
     */
    MOBILE("数据网络"),
    /**
     * WIFI网络
     */
    WIFI("WIFI网络"),
    /**
     * 无网络
     */
    NO_NETWORK("无网络");
    private String msg;
    NetWorkEnum(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
