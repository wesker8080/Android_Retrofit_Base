package com.elite.etl.model;

/**
 * @Author: Wesker
 * @Date: 2019-05-14 9:57
 * @Version: 1.0
 */
public enum VoiceEnum {
    /**
     * 服务开始
     */
    SERVICE_START(0, "服务开始"),
    /**
     * 服务确认
     */
    SERVICE_CONFIRM(1, "服务确认"),
    /**
     * 服务结束
     */
    SERVICE_STOP(2, "服务结束"),
    /**
     * 服务评价
     */
    SERVICE_COMMENT(3, "服务评价");

    VoiceEnum(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }
    private int value;
    private String msg;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
